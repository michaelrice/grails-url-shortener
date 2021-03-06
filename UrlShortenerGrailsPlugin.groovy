class UrlShortenerGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp",
        "grails-app/taglib/**",
        "grails-app/utils/**"
    ]

    def title = "Grails Url shortener"
    def author = "Iván López"
    def authorEmail = "lopez.ivan@gmail.com"
    def description = '''\
This is a grails plugin that integrates a custom url shortener inside your Grails application.
'''

    // URL to the plugin's documentation
    def documentation = "https://github.com/lmivan/grails-url-shortener/blob/master/README.md"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Kaleidos", url: "http://kaleidos.net" ]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "GITHUB", url: "https://github.com/lmivan/grails-url-shortener/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/lmivan/grails-url-shortener" ]

    def doWithSpring = {
        def shortenerConfig = application.config.shortener

        if (!shortenerConfig.characters) {
            log.error "ERROR: UrlShortener characters to generate the urls not found. The property shortener.characters must be defined in Config.groovy"
        }
        if (!shortenerConfig.minLength) {
            log.error "ERROR: UrlShortener minimum length not found. The property shortener.minLength must be defined in Config.groovy"
        }
        if (!shortenerConfig.shortDomain) {
            log.error "ERROR: UrlShortener short domain not found. The property shortener.shortDomain must be defined in Config.groovy"
        }

        sequenceGenerator(net.kaleidos.shortener.generator.DummySequenceGenerator)
    }
}
