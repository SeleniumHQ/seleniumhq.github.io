# Local-only workspace

This directory is for repo-local private work that should not be committed:

- personal agent instructions, preferably `.local/AGENTS.md`;
- personal agent skills under `.local/agent/skills/local-*`;
- scratch code;
- private experiments;
- local helper scripts;
- generated outputs;
- temporary notes and prompts.

Everything in this directory is ignored by Git except this README.

Do not make production code, tests, CI, release tooling, or public documentation depend on files
in this directory. If a local experiment becomes useful to the project, move it into a tracked
source, test, docs, example, or scripts directory and review it normally.
