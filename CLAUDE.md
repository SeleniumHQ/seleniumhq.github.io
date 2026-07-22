This repository uses @AGENTS.md as the canonical repo instruction file.

Run `scripts/link-claude-skills.sh` at the start of a session. Claude Code only discovers
skills in `.claude/skills/`, not `.agents/skills/` where this repo keeps them, so they are not
available until it runs. It is safe to re-run and stays silent when nothing changed. Relay
anything it reports.
