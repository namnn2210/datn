$("#lang-en").click(function () {
    if (window.location.href.toString().includes("?")) {
        if (window.location.href.toString().includes("?lang=vi")) {
            window.location = window.location.href.toString().replace("?lang=vi", "?lang=en")
        } else {
            window.location = window.location.href.toString() + '&lang=en'
        }
    } else {
        window.location = window.location.href.toString() + '?lang=en'
    }
    if (window.location.href.toString().includes("&lang=vi")) {
        window.location = window.location.href.toString().replace("&lang=vi", "&lang=en")
    }
})

$("#lang-vi").click(function () {
    if (window.location.href.toString().includes("?")) {
        if (window.location.href.toString().includes("?lang=en")) {
            window.location = window.location.href.toString().replace("?lang=en", "?lang=vi")
        } else {
            window.location = window.location.href.toString() + '&lang=vi'
        }
    } else {
        window.location = window.location.href.toString() + '?lang=vi'
    }
    if (window.location.href.toString().includes("&lang=en")) {
        window.location = window.location.href.toString().replace("&lang=en", "&lang=vi")
    }
})