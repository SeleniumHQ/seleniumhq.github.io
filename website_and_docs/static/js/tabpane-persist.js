<!-- Upstream docsy commit — ecd4be87ea48e8e94684e32c925049e9bdf7f127-->
if (typeof Storage !== 'undefined') {
    let activeLanguage = localStorage.getItem('active_language');

    // Get active language from URL params if exists
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    if (params.language !== null) {
        activeLanguage = params.language;
        localStorage.setItem('active_language', activeLanguage);
    }
    if (activeLanguage) {
        document
            .querySelectorAll('.persistLang-' + activeLanguage)
            .forEach((element) => {
              $('#' + element.id).tab('show');
            });
    }
}
function persistLang(language) {
    if (typeof Storage !== 'undefined') {
        localStorage.setItem('active_language', language);
        document.querySelectorAll('.persistLang-' + language)
          .forEach((element) => {
            $('#' + element.id).tab('show');
        });
    }
}
