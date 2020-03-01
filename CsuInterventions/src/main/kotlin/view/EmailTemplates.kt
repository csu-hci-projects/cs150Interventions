package edu.colostate.csedu.view

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Template
import com.github.jknack.handlebars.io.ClassPathTemplateLoader
import edu.colostate.csedu.SETTINGS


/**
 *
 *
 *
 * @author Albert Lionelle <br>
 *         lionelle@colostate.edu <br>
 *         Computer Science Department <br>
 *         Colorado State University
 * @version 1.0
 */
class EmailTemplates {

    private  var loader : ClassPathTemplateLoader = ClassPathTemplateLoader()
    private  var handlebars: Handlebars

    fun compileTemplate(bodyTemplate: String, fromFile: Boolean = true) = if(fromFile) {
            handlebars.compile(bodyTemplate)
        } else {
            handlebars.compileInline(bodyTemplate)
        }

    fun applyTemplate(template: Template, properties: Map<String, Any>) : String {
        return  template.apply(properties)
    }

    fun compileAndApplyTemplate(bodyTemplate: String, properties : Map<String, Any>, fromFile : Boolean = true) : String {
        return applyTemplate(compileTemplate(bodyTemplate, fromFile), properties)
    }


    init {
        loader.prefix = SETTINGS.TEMPLATE_PATH
        handlebars = Handlebars(loader)
    }


}