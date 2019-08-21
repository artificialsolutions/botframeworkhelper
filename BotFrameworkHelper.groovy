/*
   Copyright 2019 Artificial Solutions
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
       http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

/*
Name: BotFrameworkHelper
Description: Helper class for https://github.com/artificialsolutions/tie-api-example-ms-bot-framework
Version: 1.1
Supported tie-api-example-ms-bot-framework version: 1.1.x or higher
*/

public class BotFrameworkHelper {

    // SUGGESTED ACTIONS
    public static String createSuggestedActionsJSON(List actionList){
      
        def suggestedActions = [:]
        suggestedActions.actions = actionList
      
        def resultString = new groovy.json.JsonBuilder(suggestedActions).toString()

        return resultString
    }
   
   
    public static Map createSuggestedActionButton(def title = "", def value = ""){
      
        def result = [:]

        result.type = "imBack" //required to send a clicked button's value back into a chat as input.
        result.title = title
        result.value = value
      
        return result
    }

    // HERO CARDS
    public static List createHeroCardImage(def url = "", def altText = ""){
        
        def imageList = []
        
        try {
            def image = [:]
            image.url = url
            image.altText = altText
            
            imageList << image
            
        } catch (Exception e){
            println "Error createHeroCardImage: " + e.getMessage()
        }

        return imageList
    }
    
    public static Map createPostbackButton(def buttonTitle = "", def buttonText = ""){
        
        def button = [:]
        
        try {
            button.title = buttonTitle
            button.text = buttonText
            button.type = "postBack"
            
        } catch (Exception e){
            println "Error createPostbackButton: " + e.getMessage()
        }
    
        return button
    }
    
    public static String createHeroCardJSON (def title = "", def subTitle = "", def text = "", List buttonsList = null, List imagesList = null) {
        def result = ""
        try {
            def content = [:]
        
            if (title) {
                content.title = title
            }

            if (subTitle) {
                content.subtitle = subTitle
            }

            if (text) {
                content.text = text
            }

            if (buttonsList) {
                content.buttons = buttonsList
            }

            if(imagesList){
                content.images = imagesList
            }

            def attachment = [
            	'contentType':'application/vnd.microsoft.card.hero',
            	'content': content
            ]

            result = new groovy.json.JsonBuilder(attachment).toString()

        } catch (Exception e) {
            println "Error createHeroCardJSON: " + e.getMessage()
        }

        return result 
    }
    
    
    // MEDIA ATTACHMENTS
    public static String createImageJSON(def url = "", def name = "") {
    
        def result = ""
        
        try {
            
            def contentType = "image/"
            def extension = url.split("\\.")[-1]
            
            if (extension == "jpg") {
                contentType += "jpeg"
            } else {
                contentType += extension
            }

            def image = [
                'contentType' : contentType,
                'contentUrl': url,
                'name': name
            ]
        
            result = new groovy.json.JsonBuilder(image).toString()
            
        } catch (Exception e){
            println "Error createImageJSON: " + e.getMessage()
        }
    
        return result
    }

    public static String createVideoJSON(def videoUrl = "", def name = "") {
    
        def result = ""
        
        try {
            
            def contentType = "video/"
            def extension = videoUrl .split("\\.")[-1]
            
            contentType += extension

            def video = [
                'contentType' : contentType,
                'contentUrl': videoUrl,
                'name': name
            ]
            
            result = new groovy.json.JsonBuilder(video).toString()
            
        } catch (Exception e){
            println "Error createVideoJSON: " + e.getMessage()
        }
        
        return result
    }
    
}