# Selenium Redesign
(Original README handed by the template creators) 

## Quick start
To view progress, you will first need to [install Hugo](https://gohugo.io/getting-started/installing/)
on your machine.

Once Hugo is installed, navigate to the root directory of the project and run `hugo server`.

You should be able to view the WIP site at `localhost:1313`.

## Editing data & content
Various components of the site are driven by content found in the `/content` and `/data` folders.
This section will explain how to add and remove content from these sections.

### Blog
Blog content can be found in `/content/blog`. Markdown files written to this folder will be displayed on the 
Blog section of the site, and previewed on the home page. All blog posts should have a metadata section 
at the top of the file:
```$xslt
+++
Description = "We are very pleased to announce the release of Selenium 3.0.
Title = "Selenium 3.0: Out Now!"
Date = 2016-10-13
+++
```

#### Blog Archive
Hugo doesn't come with a way to archive content by date, but there's a relatively simple workaround.
To create a new year for the archive, do the following:
- Create a content page in `content/archive/<year>.md` with an appropriate date value - see `content/archive/2016.md` for an example.
- Add a link to the new year in `themes/selenium/layouts/partials/blog-filter` that links to `/blog/<year>`.

Posts dated in a given <year> will automatically appear in the `/blog/<year>` archive.

### Documentation
Documentation HTML files provided by the Selenium project can be found in `/content/documentation`. Per Hugo
convention, the Documentation homepage can be found in `/themes/selenium/layouts/documentation/section.html`.

As seen in many of the HTML files in `/content/documentation`, pages can link to other pages like so:

_ex_: link to `quick.html`:
`<a href="/documentation/quick">Quick tour</a>`

### Downloads
#### Previous releases
Content for the Previous Releases modal on the Download page is stored in `/data/releases.json`. To add a new expandable
section to the modal, add an entry to `/data/releases.json` matching the format shown below. Each entry in "links"
represents a new row that will be displayed in the expanded version section.
```
{
// ...
"3.7": {
    "version": "3.7",
    "links": {
      "IEDriverServer_Win32_3.7.0.zip": {
        "name": "IEDriverServer_Win32_3.7.0.zip",
        "url": "https://selenium-release.storage.googleapis.com/3.7/IEDriverServer_Win32_3.7.0.zip",
        "lastModified": "2017-11-03 01:37:52",
        "size":"0.85MB",
        "etag": "18bd8ca48f4fe2074e121793502b2100",
        "version": "3.7"
      },
      "IEDriverServer_x64_3.7.0.zip": {
        "name": "IEDriverServer_x64_3.8.0.zip",
        "url": "https://selenium-release.storage.googleapis.com/3.7/IEDriverServer_x64_3.7.0.zip",
        "lastModified": "2017-11-03 01:37:51",
        "size":"0.98MB",
        "etag": "ab4ffe9461002f140659fcfb3d98b5f3",
        "version": "3.7"
      }
    }
  }
}
``` 

#### Language bindings
The list of Selenium Client & WebDriver Language Bindings on the Downloads page can be extended via its content files.
To add a new row to the section, simply add a new file to `/content/downloads`. Conform to the format found in, for example,
`/content/downloads/java.md`, and the new content will automatically be shown on the Downloads page.

### Ecosystem
List content for the Ecosystem page is stored in `/content/ecosystem`. Hugo determines which list the content belongs in
based on the `Key` attribute found in the markdown file.

#### Browser drivers
Look to `/content/ecosystem/chromedriver.md` for an example here. Hugo will add any `/content/ecosystem/*.md` file 
containing the `Key = "drivers"` field to the Third Party Browser Drivers list.

#### Language bindings
Look to `/content/ecosystem/perl.md` for an example here. Hugo will add any `/content/ecosystem/*.md` file 
containing the `Key = "bindings"` field to the Third Party Browser Drivers list.

#### Selenium Plugins
Look to `/content/ecosystem/flex-pilot.md` for an example here. Hugo will add any `/content/ecosystem/*.md` file 
containing the `Key = "selenium-plugins"` field to the Third Party Browser Drivers list.

## Hosting solutions
Hugo's site has a great list of [hosting solutions available](https://gohugo.io/hosting-and-deployment/).

Though we don't explicitly recommend any solution, 
Netlify, Gitlab, and AWS Amplify are popular & well supported options.
