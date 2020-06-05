#!/bin/bash

echo -e "\033[0;32mDeleting Hugo previously generated directories for docs and main site...\033[0m"
rm -rf site_source_files/public && rm -rf docs_source_files/public

echo -e "\033[0;32mGenerating Hugo site for docs...\033[0m"
cd docs_source_files && hugo && cd ..

echo -e "\033[0;32mGenerating Hugo site for main website...\033[0m"
cd site_source_files && hugo && cd ..

echo -e "\033[0;32mMerging both sites into a single one...\033[0m"
mv docs_source_files/public/* site_source_files/public/documentation

echo -e "\033[0;32mRefreshing contents of the published directory...\033[0m"
#rm -rf $(ls site_source_files/public)
#mv site_source_files/public/* .

echo -e "\033[0;32mDone building site!\033[0m"
