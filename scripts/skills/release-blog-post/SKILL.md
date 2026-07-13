---
name: release-blog-post
description: Generate the announcement blog post (and LinkedIn image) for a new Selenium 4.x release, by scaffolding from the previous post, summarizing the GitHub changelog, sorting contributors, and compositing the release image. Use when a new Selenium version (e.g. 4.41) has just been released and needs its blog post.
---

# Release blog post

Produces the `selenium-4-N-released.md` post and its `selenium_4.N.jpg` share image for a
freshly released Selenium version, following the same structure as previous posts under
`website_and_docs/content/blog/*/selenium-4-*-released.md`.

Run every step from the repo root. Ask the user for the new version (e.g. `4.41`) if it
isn't already known — everything else below is derived from it.

## 1. Scaffold

```
scripts/release-updates.sh 4.41
```

This, for version 4.41 (previous = 4.40):

- Bumps `4.40.x` → `4.41.0` in `website_and_docs/layouts/partials/selenium-clients-and-webdriver-bindings.html`
  and `website_and_docs/layouts/downloads/list.html`.
- Copies the previous release's blog post to
  `website_and_docs/content/blog/<year-of-release>/selenium-4-41-released.md`, `git add`s it,
  replaces `4.40` → `4.41` throughout, and sets `date:` to the `selenium-4.41.0` tag's commit
  date (the year is derived the same way, so this works across year boundaries).
- Prints three deduped, sorted `{{< gh-user ... >}}` lists — Selenium, Docs, and Docker
  contributors — for commits between the `selenium-4.40.0` and `selenium-4.41.0` tags.

Everything after this point is manual editing of the new blog file using the data gathered
in the remaining steps. The copied file still has the *previous* release's prose and
contributor lists — all of it needs replacing, not just the version string.

## 2. Split contributors: external vs. Selenium Team

The Selenium Team roster is the set of `gh-user` logins already listed on
`website_and_docs/content/project/structure/_index.html` (Project Leadership Committee plus
all per-binding committers) — extract it with:

```
grep -oE 'api.github.com/users/[A-Za-z0-9_.\[\]-]+' website_and_docs/content/project/structure/_index.html \
  | sed 's#api.github.com/users/##' | sort -u -f
```

(The `seleniumhq/selenium-tlc` and `seleniumhq/selenium-committers` GitHub teams are close but
incomplete for this purpose — several names on the structure page, e.g. `joerg1985`,
`nvborisenko`, `VietND96`, aren't members of either GitHub team. Use the structure page, not
the teams API.)

Also drop bot/automation accounts from all three lists before anything else — they show up in
the raw commit data but were never credited in past posts: `dependabot[bot]`,
`github-actions[bot]`, `renovate[bot]`, `selenium-ci`.

For each of the three (now bot-free) contributor lists from step 1:

- Logins that appear on the structure-page roster → remove from that list, and add to one
  merged, deduped, alphabetically-sorted (case-insensitive) "Selenium Team Members" list
  instead.
- Everyone else stays under their original section (Selenium / Selenium Docs & Website /
  Docker Selenium).

If a contributor list ends up empty after removing team members, omit that `###` section
for this release rather than leaving an empty block.

## 3. Draft the changelog sections

Fetch the release notes for the new tag and the recent docker-selenium activity:

```
gh api repos/seleniumhq/selenium/releases/tags/selenium-4.41.0 --jq '.body'
gh api --method GET /repos/seleniumhq/docker-selenium/commits -f since="<SINCE_COMMIT_DATE>" -f until="<UNTIL_COMMIT_DATE>" -f per_page=1000 --jq '.[].commit.message'
```

(`SINCE_COMMIT_DATE`/`UNTIL_COMMIT_DATE` are the same tag commit dates step 1 already computed —
rerun the two `gh api repos/seleniumhq/selenium/commits/selenium-4.X.0 --jq '.commit.committer.date'`
calls if needed.)

Read the PR titles in the changelog body and write, in the same tone as prior posts:

- **✨ Highlights**: 5-7 bullets, one per notable theme (new BiDi commands, a new class,
  Chrome DevTools version bump, Grid behavior changes, build/infra work). Bold the binding
  name in brackets when a bullet is specific to one, e.g. `**[Java]** ...`. Check whether the
  changelog mentions new Chrome DevTools protocol versions and call them out first if so.
- **📦 Notable Changes**: one `###` subsection per binding (Java, Python, Ruby, .NET, Grid,
  Build & Infra) with 3-5 bullets each, paraphrasing the PR titles — don't just paste them
  verbatim.
- **🐳 Docker Selenium**: bullet list of the docker-selenium commit messages (they already
  include their PR number, e.g. `Docker: update X (#3002)`), followed by the fixed line
  `[See all changes](https://github.com/SeleniumHQ/docker-selenium/releases)`.

Skip any of the binding subsections that had no meaningful changes rather than inventing
content.

## 4. Edit the blog file

In the scaffolded file:

- Replace the `## ✨ Highlights`, `## 📦 Notable Changes`, and Docker Selenium sections with
  the drafts from step 3.
- Replace each `### [Selenium](...)`, `### [Selenium Docs & Website](...)`, and
  `### [Docker Selenium](...)` contributor block with the non-team logins from step 2, each
  as `{{< gh-user "https://api.github.com/users/<login>" >}}` on its own line.
- Replace the `### [Selenium Team Members][team]` block with the merged team-member list
  from step 2.
- Double-check `date:` and the `images:` path both reflect the new version (step 1 sets
  these, but verify — `images:` should point at `/images/blog/<year>/selenium_4.41.jpg`).

## 5. Generate the release image

```
scripts/generate-release-image.sh 4.41 <release-date YYYY-MM-DD> website_and_docs/static/images/blog/<year>/selenium_4.41.jpg
```

This composites "Selenium 4.41 Released" and the Selenium logo over a gradient background,
auto-picked by season from the release date (no photo needed). If the user supplies a
background photo instead (matching the look of some earlier posts, e.g. a stock photo), pass
it as a 4th argument:

```
scripts/generate-release-image.sh 4.41 <release-date> <output.jpg> /path/to/background.jpg
```

Read the resulting image back to sanity-check text placement and legibility before moving on
— regenerate (a different `--seed`-driven gradient will differ slightly) if the text is hard
to read against the background.

## 6. Review

- Reread the finished post end to end against `website_and_docs/content/documentation/about/style.en.md`.
- Run `./build-site.sh` (or `cd website_and_docs && hugo server`) if practical, and check the
  post renders — especially the `gh-user` shortcodes and the image.
- Do not commit or push — leave the changes for the user to review first, per their usual
  workflow.
