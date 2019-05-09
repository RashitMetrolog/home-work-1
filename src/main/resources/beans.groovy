
import ru.itpark.groovy.GroovyCachedAnnotationBPP
import ru.itpark.groovy.GroovyPostService
import ru.itpark.groovy.GroovyRequestClient
import ru.itpark.util.CustomPropertyPlaceholderConfigurer

beans {
    propertyPlaceholderConfigurer CustomPropertyPlaceholderConfigurer, {
        locations = 'application.json'
    }

    groovyCachedAnnotationBPP GroovyCachedAnnotationBPP

    groovyRequestClient GroovyRequestClient

    postService GroovyPostService, ref(groovyRequestClient), '${jsonPath}', '${url}'
}