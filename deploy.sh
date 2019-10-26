#!/bin/bash

if git --no-pager log -1 --oneline | grep '[deploy site]'; then
  echo -e "\033[0;32mDeploying Selenium site to GitHub...\033[0m"

  if [ -n "$GITHUB_AUTH_SECRET" ]
  then
      touch ~/.git-credentials
      chmod 0600 ~/.git-credentials
      echo "$GITHUB_AUTH_SECRET" > ~/.git-credentials
      git config credential.helper store
      git config user.email "selenium-ci@users.noreply.github.com"
      git config user.name "Selenium CI Bot"
  fi

  git checkout master
  git pull
  chmod +x "$TRAVIS_BUILD_DIR"/build-site.sh && "$TRAVIS_BUILD_DIR"/build-site.sh
  git add .
  git commit -m "Deploying on $(date), commit ${TRAVIS_COMMIT} and job ${TRAVIS_JOB_NUMBER}, [skip ci]" || true
  git push
else
  echo -e "\033[0;32mCommit to master did not trigger deployment, add [deploy site] to the commit message if you intend to deploy...\033[0m"
fi
