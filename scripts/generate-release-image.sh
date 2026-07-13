#!/bin/bash
# Generates the "Selenium X.Y Released" image used at the top of a release blog
# post and for social/LinkedIn sharing.
#
# Usage:
#   scripts/generate-release-image.sh <version> <date:YYYY-MM-DD> <output.jpg> [background_image]
#
# <version>          e.g. 4.41
# <date>              release date, used only to pick a seasonal palette when no
#                      background image is supplied
# <output.jpg>         where to write the composited image
# [background_image]   optional photo/illustration to use instead of the
#                       generated gradient (any format ImageMagick can read)

set -euo pipefail

VERSION="$1"
RELEASE_DATE="$2"
OUTPUT="$3"
BACKGROUND="${4:-}"

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
REPO_ROOT=$(cd "$SCRIPT_DIR/.." && pwd)
LOGO="$REPO_ROOT/website_and_docs/static/images/selenium_logo_square_green.png"

WIDTH=1280
HEIGHT=854

FONT=""
for candidate in \
    "/System/Library/Fonts/Supplemental/Arial Rounded Bold.ttf" \
    "/System/Library/Fonts/Supplemental/Arial Bold.ttf" \
    "/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf"; do
    if [ -f "$candidate" ]; then
        FONT="$candidate"
        break
    fi
done
if [ -z "$FONT" ]; then
    FONT="Helvetica-Bold"
fi

MAGICK=magick
if ! command -v magick >/dev/null 2>&1; then
    MAGICK=convert
fi

TMP_BG=$(mktemp -t release-image-bg).jpg
trap 'rm -f "$TMP_BG"' EXIT

if [ -n "$BACKGROUND" ]; then
    "$MAGICK" "$BACKGROUND" -resize "${WIDTH}x${HEIGHT}^" -gravity center -extent "${WIDTH}x${HEIGHT}" "$TMP_BG"
else
    MONTH=$(date -j -f "%Y-%m-%d" "$RELEASE_DATE" "+%m" 2>/dev/null || date -u -d "$RELEASE_DATE" "+%m" 2>/dev/null || echo "01")
    MONTH=$((10#$MONTH))

    if [ "$MONTH" -ge 3 ] && [ "$MONTH" -le 5 ]; then
        C1="#4f9e6b"; C2="#bfe0a8"; ANGLE=80   # spring
    elif [ "$MONTH" -ge 6 ] && [ "$MONTH" -le 8 ]; then
        C1="#2f8fc2"; C2="#f2c14e"; ANGLE=95   # summer
    elif [ "$MONTH" -ge 9 ] && [ "$MONTH" -le 11 ]; then
        C1="#7a3d1f"; C2="#d97a2e"; ANGLE=60   # autumn
    else
        C1="#6f5a9e"; C2="#e8875f"; ANGLE=55   # winter
    fi

    "$MAGICK" -size "${WIDTH}x${HEIGHT}" -define gradient:angle=$ANGLE gradient:"$C1"-"$C2" \
        -seed "$VERSION" -attenuate 0.35 +noise Gaussian -blur 0x25 \
        "$TMP_BG"
fi

TEXT="Selenium\n${VERSION}\nReleased"

"$MAGICK" "$TMP_BG" -resize "${WIDTH}x${HEIGHT}" -gravity West \
    -font "$FONT" -pointsize 92 -fill white \
    -annotate +80+0 "$TEXT" \
    "$TMP_BG"

"$MAGICK" "$TMP_BG" \( "$LOGO" -resize 440x440 \) -gravity East -geometry +60+0 -composite \
    -quality 90 "$OUTPUT"

echo "Wrote $OUTPUT"
