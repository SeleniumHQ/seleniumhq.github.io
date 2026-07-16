#!/bin/sh
# Sync this repo's shared skills from .agents/skills/ into .claude/skills/.
#
# Claude Code only discovers skills in .claude/skills/, and does not read .agents/skills/
# where this repo keeps its shared skills. Other agents (e.g. Cursor, Windsurf) read the
# .agents/ location directly and do not need this script.
#
# Opt-in: nothing runs this for you. Run it by hand, or from the post-checkout, post-merge,
# and post-rewrite git hooks to re-sync after checkout, pull, and rebase.
#
# Reports what it changed and stays silent when already in sync, so it is safe to re-run.
# Where symlinks are unavailable (typically Windows) it copies instead, and says so on every
# run, since copies are only as fresh as the last run.
#
# Writes only to .claude/, which is gitignored. Personal skills you place in .claude/skills/
# yourself are never touched.
set -eu

root=$(git rev-parse --show-toplevel) || exit 0
src="$root/.agents/skills"
dst="$root/.claude/skills"

# Marks a copy this script made, so it can be refreshed or removed safely.
MARKER=".skill-sync"

# True only for entries this script created: a link into .agents/skills/, or a marked copy.
# Anything else in .claude/skills/ belongs to the user and is never written to or removed.
owned() {
    if [ -L "$1" ]; then
        case "$(readlink "$1")" in
            ../../.agents/skills/*) return 0 ;;
            *) return 1 ;;
        esac
    fi
    [ -f "$1/$MARKER" ]
}

[ -d "$src" ] || exit 0
mkdir -p "$dst"

changed=0

# Remove our own entries whose source skill is gone (renamed or deleted upstream).
for target in "$dst"/*; do
    [ -e "$target" ] || [ -L "$target" ] || continue
    name=$(basename "$target")
    [ -d "$src/$name" ] && continue
    if owned "$target"; then
        rm -rf "$target"
        echo "removed $name (no longer in .agents/skills)"
        changed=1
    fi
done

copied=0

for skill in "$src"/*/; do
    [ -f "$skill/SKILL.md" ] || continue
    name=$(basename "$skill")
    target="$dst/$name"
    link_to="../../.agents/skills/$name"

    # Already linked correctly: nothing to do, nothing to report.
    if [ -L "$target" ] && [ "$(readlink "$target")" = "$link_to" ]; then
        continue
    fi

    if { [ -e "$target" ] || [ -L "$target" ]; } && ! owned "$target"; then
        echo "skipped $name (a skill of your own has that name)" >&2
        continue
    fi

    # A marked copy already exists: refresh it, since it may be stale.
    refreshing=0
    [ -e "$target" ] && refreshing=1

    rm -rf "$target"

    # ln -s can succeed while silently producing a copy (Git Bash), so test the result
    # rather than trusting the exit status.
    if ln -s "$link_to" "$target" 2>/dev/null && [ -L "$target" ]; then
        echo "linked $name"
        changed=1
        continue
    fi

    rm -rf "$target"
    cp -R "$skill" "$target"
    : > "$target/$MARKER"
    copied=$((copied + 1))
    if [ "$refreshing" = 0 ]; then
        echo "copied $name"
        changed=1
    fi
done

# Said on every run, not just the first: a copy is only as fresh as the last run, and the
# user needs to know that whenever it applies.
if [ "$copied" -gt 0 ]; then
    echo "warning: this filesystem does not support symlinks, so $copied skill(s) were copied."
    echo "warning: edits to .agents/skills/ do not reach Claude until this script runs again."
fi

# A change here means nothing is keeping this in sync on its own.
if [ "$changed" = 1 ]; then
    echo "tip: run this from applicable git hooks to keep skills in sync automatically."
fi

exit 0
