# AGENTS.md

## Project overview

- Purpose: official Selenium website and documentation, built with Hugo and Docsy.
- Primary content: Markdown, HTML, Hugo templates, site assets, structured data, and runnable
  examples in Java, Python, .NET, Ruby, JavaScript, and Kotlin.
- Setup and installation details live in `README.md`. Use its Quick start section for the current
  Hugo Extended version, Docsy requirements, and local setup guidance.

## Local contributor customization

- The `.local/` directory is available for customization, generated artifacts, scratch work,
  and temporary files. It is ignored by Git except for `.local/README.md`.
- A contributor may create `.local/AGENTS.md` for personal repo-specific instructions and
  preferences. Before beginning any task, check whether `.local/AGENTS.md` exists; if it
  exists, read it with your file-reading tool and apply it as the contributor's local
  instruction overlay.
- If `.local/agent/skills/` exists, inspect its `*/SKILL.md` files and treat them as
  additional user-defined skills.

## Project-defined skills

- `scripts/skills/` holds shared, repo-specific skills committed to the codebase (as opposed
  to `.local/agent/skills/`, which is personal and untracked). Inspect its `*/SKILL.md` files
  and treat them as additional skills available in this repo.

## Repository map

- `website_and_docs/`: Hugo site root. Run local Hugo commands from here.
- `website_and_docs/content/`: Published site content, including docs, blog posts, project pages,
  and translations.
- `website_and_docs/content/documentation/`: Main Selenium documentation. Read
  `about/style.en.md` for documentation style conventions before editing docs.
- `website_and_docs/layouts/`: Hugo templates and shortcodes.
- `website_and_docs/assets/`, `website_and_docs/static/`, `website_and_docs/data/`: Site assets,
  static files, and structured data.
- `examples/`: Runnable examples referenced from docs through `gh-codeblock`.
- `scripts/`, `build-site.sh`, `netlify.toml`: Build, preview, release, and deploy support.
  `scripts/skills/` holds committed, repo-specific Claude skills (see "Project-defined skills").
- `website_and_docs/public/`, `website_and_docs/resources/`: Generated Hugo output/cache; avoid
  manual edits.

## Standard commands

- Preview site locally: `cd website_and_docs && hugo server`
- Preview without Hugo cache: `cd website_and_docs && hugo server --ignoreCache`
- Build site like CI: `./build-site.sh`
- Java examples: `cd examples/java && mvn test`
- Python examples: `cd examples/python && pytest`
- JavaScript examples: `cd examples/javascript && npm test`
- Ruby examples: `cd examples/ruby && bundle exec rspec`
- Ruby example lint: `cd examples/ruby && bundle exec rubocop`
- .NET examples: `cd examples/dotnet/SeleniumDocs && dotnet test`
- Kotlin examples: `cd examples/kotlin && mvn test`

## Working guidance

- Prefer small, focused changes that match the existing structure and naming.
- Follow `website_and_docs/content/documentation/about/style.en.md` for docs style. In particular,
  keep prose language-independent, put binding-specific code in tabs, use `gh-codeblock` for
  runnable snippets, and do not indent `gh-codeblock` lines.
- Changes under `examples/` probably require matching markdown updates. Search
  `website_and_docs/content` for affected `gh-codeblock` paths and update line ranges after moving,
  adding, or removing lines.
- For docs/layout changes, run `./build-site.sh` when practical. For example changes, run the
  relevant binding's example tests and check affected `gh-codeblock` references.
- For PR review tasks, read `.github/pr_review.md` before reviewing.
- GitHub Actions builds the site on PRs touching `website_and_docs/**`. Deploys occur from `trunk`
  when a commit message contains `[deploy site]`; output is pushed to `publish`.
- Never commit secrets, tokens, cookies, private keys, or local credentials. Tracked project files
  must not depend on `.local/`.
