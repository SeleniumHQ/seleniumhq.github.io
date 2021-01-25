+++
Description = "Postmortem: Selenium.dev outage"
Title = "Postmortem: Selenium.dev outage"
Date = 2021-01-24
Author = "Sri Harsha"
AuthorLink = "https://twitter.com/harsha509"
tags = ["selenium"]
categories = ["general"]
+++

## Impact:
On January 24th, 2021, we experienced a major service outage for Selenium.dev, the official documentation for SeleniumHQ.

## Root Cause:
The outage was caused by an accidental deletion of repository ‘SeleniumHQ/seleniumhq.github.io’ from the organization repositories rather than the fork it was intended for. As the user had admin access to the repository, this began deletion of repository, associated PR’s, Issues, and site configuration data.
Timeline (incident occurred and was recovered from during 2021-01-24, IST):
* **~1:10 PM IST**: JS31096 deletes the repository from SeleniumHQ
* **OUTAGE BEGINS**
* **~1:13 PM IST**: https://www.selenium.dev/ goes down.
* **~1:15 PM IST**: JS30196 reports incident to @diemol and @Corevo.
* **~1: 20 PM IST**: diemol writes to GitHub for repository recovery.
* **~1: 20 PM IST**: Corevo suggests committing backup code.
* **~1:22 PM IST**: JS31096 commits a backup code (< 30 min old)
* **~2:58 PM IST**: diemol commits the backup code to SeleniumHQ
* **~3:10 PM IST**: diemol deploys the site with changes until January 19, 2020.
* **OUTAGE ENDS**
* **~6:59 PM IST**: GitHub support replies to diemol on restoring the deleted repository.
* **~8:16 PM IST**: Repository is successfully restored with GitHub support, and the site is up and running with no changes lost.

## Lessons Learnt
### Things that went well
* Help from the team members available in Selenium Slack (mainly @diemol) helped us to bring back the site up and running within a couple of hours.
### Where we got lucky
* Availability of the backup repository with the team members helped us to make site up and running within a couple of hours.
