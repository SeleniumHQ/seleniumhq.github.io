# find and copy:
find . -iname "*.en.md" -exec sh -c 'f="{}"; cp "$f" "${f%.en.md}.de.md"' \;

# find copied files
DE_MD_FILES=$(find . -iname "*.de.md")

# desired text in _de.md files
DESIRED_TEXT="german not translated yet"

# see if de_md_files were found
if [ ! -z "$DE_MD_FILES" ]
then
    #count files and print info message
    FILE_COUNT=$(echo "$DE_MD_FILES" | grep -c '^')
    # debugging purposes, can be deleted without harming anything
    echo "::: \t $FILE_COUNT files were found. replacing text.."

    for de_file in $DE_MD_FILES
    do        
        # debugging purposes, can be deleted without harming anything
        echo "::: \t..in $de_file"
        
        # empty en_md_file
        # echo "$DESIRED_TEXT" > $de_file

        # gewünschter Text UND danach der original Text aus dem copied file
        #echo "$DESIRED_TEXT\n$(cat $de_file)" > $de_file
    done

else
    echo "no files with extension .de.md found"
fi
