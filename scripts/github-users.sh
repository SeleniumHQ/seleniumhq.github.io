#!/bin/bash
# Adds GitHub users referenced by gh-user shortcodes to website_and_docs/data/github_users.yml,
# looking up each display name with gh. Users already in the file are left alone.
#
# Usage:
#   scripts/github-users.sh [path ...]   (defaults to website_and_docs/content)

set -e

USERS_FILE=website_and_docs/data/github_users.yml

if [ ! -f "$USERS_FILE" ]; then
    echo "# Maintained by scripts/github-users.sh. Keys are lowercase GitHub logins." > "$USERS_FILE"
fi

ADDED=0
for LOGIN in $(grep -rhoE 'gh-user "https://api\.github\.com/users/[^"]+"' "${@:-website_and_docs/content}" \
    | sed -E 's#.*/users/([^"]+)"#\1#' | tr '[:upper:]' '[:lower:]' | sort -u); do
    case "$LOGIN" in
        *\[bot\]|selenium-ci) continue ;;
    esac
    grep -qF "\"$LOGIN\":" "$USERS_FILE" && continue
    if NAME=$(gh api "users/$LOGIN" --jq '.name // "" | tojson'); then
        echo "\"$LOGIN\": $NAME" >> "$USERS_FILE"
        echo "Added $LOGIN: $NAME"
        ADDED=$((ADDED + 1))
    else
        echo "Could not look up $LOGIN; it will render as @$LOGIN" >&2
    fi
done

{ head -n 1 "$USERS_FILE"; tail -n +2 "$USERS_FILE" | LC_ALL=C sort; } > "$USERS_FILE.tmp"
mv "$USERS_FILE.tmp" "$USERS_FILE"
echo "$ADDED new user(s) added to $USERS_FILE"
