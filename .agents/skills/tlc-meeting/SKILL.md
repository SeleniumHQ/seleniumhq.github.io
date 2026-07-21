---
name: tlc-meeting
description: >-
  Use this skill when creating or updating Selenium Technical Leadership Committee meeting
  minutes for seleniumhq.github.io from a local TLC Weekly transcript, Gemini Meet notes file,
  or meeting notes file. Use it for files under website_and_docs/content/meetings, especially
  when the rolling TLC agenda must be cross-referenced. Do not use it for non-TLC meetings or for
  summaries that should remain private.
---

# TLC Meeting Minutes

## Goal

Create a public, concise, and evidence-based Selenium TLC meeting page from a local transcript.
The page should preserve decisions, reasons, tradeoffs, and explicit follow-up ownership without
requiring readers to inspect the full transcript.

## Inputs

Require a local transcript or Gemini Meet notes Markdown file. If the user does not provide a
path, ask for it before drafting.

Default rolling agenda:

```text
https://docs.google.com/document/d/18InWY44S0C_ECkRkRo8GKictu8BEtsahRsFh17tmUR0/edit?tab=t.0#heading=h.2kfu4ns4qzjl
```

When agenda text is needed, fetch the plain-text export:

```bash
curl -L "https://docs.google.com/document/d/18InWY44S0C_ECkRkRo8GKictu8BEtsahRsFh17tmUR0/export?format=txt" -o /tmp/tlc-rolling-agenda.txt
```

## Workflow

1. Identify the `seleniumhq.github.io` repo root.
2. Read the local transcript. For Gemini Meet notes, ignore `Summary`, `Decisions`, `Details`,
   and `Next steps`; use only the `Transcript` section as evidence.
3. Determine the meeting date from the transcript title, heading, or filename.
4. Fetch the rolling agenda when internet access is available, then extract only the agenda
   section matching the meeting date. The rolling agenda is a live document, not a historical
   archive, so verify the date heading before using a section. Agenda sections commonly start
   like `Jul 2, 2026 | TLC Weekly`; Markdown exports may use headings. Stop at the next dated
   meeting section.
5. Read one recent existing page under `website_and_docs/content/meetings/` only for front matter,
   intro text, and link-reference style. Use the section skeleton below for new TLC minutes.
6. When the transcript references public GitHub issues, PRs, or ADRs, read the PR/issue title,
   body, and comments if needed to identify the public link, title, and proposal context. Do not
   use GitHub discussion as evidence for a meeting decision unless the transcript discussed it.
7. Write `website_and_docs/content/meetings/YYYY/tlc-MM-DD.md`.
8. Draft the page in this shape:

   ```markdown
   ---
   title: "TLC Meeting - Month D, YYYY"
   linkTitle: "TLC - Month D, YYYY"
   date: YYYY-MM-DD
   ---

   Meetings are happening weekly on Thursday at 0700 Pacific / 1000 Eastern / 1500 UK / 1930 India as a video call, and the invite is posted to the `#selenium-tlc` channel on [Selenium Slack](https://selenium.dev/support).
   To add items to the agenda for the next meeting, please see our public [Rolling Agenda](https://docs.google.com/document/d/18InWY44S0C_ECkRkRo8GKictu8BEtsahRsFh17tmUR0/edit?tab=t.0#heading=h.2kfu4ns4qzjl)

   ### Participation

   ## Agenda

   ***

   ## Meeting Summary

   ## Decisions

   ## Discussion Notes

   ## Action Items
   ```

9. Use agenda items to order topics and keep public links that were actually discussed. Omit
   agenda-only items that the transcript does not discuss. If the rolling agenda section is
   missing or does not match the meeting date, derive the agenda from transcript-discussed topics.
10. Rewrite into publishable minutes. For each topic, capture what was proposed, what concerns or
   tradeoffs were raised, what outcome happened, and any next step.
11. Add GitHub link references only for participant handles, mapped TLC member handles mentioned
   in the page, or public links used in the page.
12. Review the final page against the transcript before finishing.

## Evidence Rules

- Treat the transcript as the source of truth for all facts, decisions, participants, owners, and
  action items.
- Use the rolling agenda only as a cross-reference for topic order and public links.
- Use GitHub PR or issue bodies and comments only as public context for referenced work. The
  transcript remains authoritative for whether the topic was discussed and what outcome occurred.
- Do not include private transcript links, meeting recording links, Gemini survey text, personal
  check-ins, or meeting logistics.
- Do not include agenda items that were not discussed.
- Do not use Gemini-generated conclusions unless the transcript supports them.
- Do not write generic plausible summaries.
- Use `agreed` only when the transcript shows agreement. Otherwise use `leaned toward`,
  `preferred`, `raised concern`, `deferred`, or similar precise language.
- Preserve qualifiers such as `in principle`, `tentative`, `preferred`, or `needs another review`;
  do not upgrade them into final decisions.
- Use `* None recorded.` under `## Decisions` or `## Action Items` when the transcript does not
  show an explicit decision or clear owner/action.

## TLC Members

Only list TLC members who participated in the transcript under `### Participation`. In all other
sections, refer to mapped TLC members by GitHub handle link, such as `[titusfortner]`, not by full
name. Use these GitHub handles and link references:

| Transcript speaker | GitHub handle |
| --- | --- |
| Alex Rodionov | `p0deje` |
| Boni Garcia or Boni García | `bonigarcia` |
| David Burns | `AutomatedTester` |
| Diego Molina | `diemol` |
| Jim Evans or James Evans | `jimevans` |
| Puja Jagani | `pujagani` |
| Sri Harsha | `harsha509` |
| Titus Fortner | `titusfortner` |

Use reference definitions in this form:

```markdown
[titusfortner]: https://github.com/titusfortner/
```

## Output Rules

- Use Markdown front matter exactly as existing meeting pages do.
- Use `Month D, YYYY` in titles and `YYYY-MM-DD` for the `date` value.
- Keep the recurring day and times in the intro, but do not state the date of the next meeting. The
  standing schedule stays accurate; a specific date written into a published page only goes stale.
- Keep the page concise enough for public minutes, not a transcript rewrite.
- Avoid duplication across sections:
  - `## Meeting Summary` gives a brief orientation in two to four sentences.
  - `## Decisions` is the authoritative list of explicit outcomes.
  - `## Discussion Notes` explains rationale, concerns, and tradeoffs without restating every
    summary sentence or decision bullet.
  - `## Action Items` lists only explicit owner/action pairs.
- Wrap prose to match existing Markdown pages, roughly 100 characters per line. Long URLs may
  remain unwrapped.
- Keep language neutral and project-focused.
- Do not create or commit checked-in transcript files.

## Validation

Before finishing, check:

- The page path, front matter date, and title match the transcript date.
- Every participant listed is a mapped TLC member who spoke or clearly participated.
- Every mapped TLC member mention uses a GitHub handle link instead of a full name.
- Every decision and action item is supported by transcript evidence.
- The agenda contains only discussed topics.
- The page contains no TODOs, private links, generated-summary artifacts, or unsupported claims.
- If practical, use a separate read-only agent to audit the page for overstatements, missing
  action items, wrong participants, private links, and agenda-only content.
