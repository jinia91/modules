package kr.co.external_api

import org.springframework.boot.SpringApplication
import org.springframework.boot.context.config.ConfigFileApplicationListener
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.boot.logging.DeferredLog
import org.springframework.context.ApplicationEvent
import org.springframework.context.event.SmartApplicationListener
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertySource
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component

@Component
class EnvironmentPostProcessorImpl : EnvironmentPostProcessor, SmartApplicationListener {

    private val log = DeferredLog()
    private val ymlLoader = YamlPropertySourceLoader()
    private val loader = PathMatchingResourcePatternResolver()

    private val resourceLocationPatterns = arrayOf(
        "classpath*:application*.yml"
    )

    private val excludeResources = arrayOf(
        "application-prod"
    )

    override fun getOrder(): Int {
        return ConfigFileApplicationListener.DEFAULT_ORDER - 1
    }

    override fun onApplicationEvent(event: ApplicationEvent) {
        if (event is ApplicationPreparedEvent) {
            log.switchTo(EnvironmentPostProcessorImpl::class.java)
        }
    }

    override fun supportsEventType(eventType: Class<out ApplicationEvent>): Boolean {
        return ApplicationEnvironmentPreparedEvent::class.java.isAssignableFrom(eventType) ||
            ApplicationPreparedEvent::class.java.isAssignableFrom(eventType)
    }

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        this.resourceLocationPatterns.forEach {
            this.loadResource(it, environment)
        }
    }

    private fun loadResource(resourceLocationPattern: String, environment: ConfigurableEnvironment) {
        val resources = this.loader.getResources(resourceLocationPattern)

        resources.forEach { resource ->
            if (this.excludeResources.contains(resource.filename)) {
                return@forEach
            }

            this.loadYml(resource)?.let {
                this.addProperties(environment, it)
                println("Loading Property : ${it.name}")
                log.info("Loading Property : ${it.name}")
            }
        }
    }

    private fun loadYml(resource: Resource): PropertySource<out Any>? {
        return this.ymlLoader.load(resource.filename, resource).getOrNull(0)
    }

    private fun addProperties(environment: ConfigurableEnvironment, propertySource: PropertySource<out Any>) {
        environment.propertySources.addLast(propertySource)
    }
}
