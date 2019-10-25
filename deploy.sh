#!/bin/bash

echo -e "\033[0;32mDeploying Selenium site to GitHub...\033[0m"

rm -rf site

if [ -n "$GITHUB_AUTH_SECRET" ]
then
    touch ~/.git-credentials
    chmod 0600 ~/.git-credentials
    echo $GITHUB_AUTH_SECRET > ~/.git-credentials
    git config credential.helper store
    git config user.email "selenium-ci@users.noreply.github.com"
    git config user.name "Selenium CI Bot"
fi

git --no-pager branch -a
#git add .
#git commit -m "Publishing on `date`, commit ${TRAVIS_COMMIT} and job ${TRAVIS_JOB_NUMBER}, [skip ci]" || true
#git push --force origin HEAD:master
