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
Version: 1.0
*/

public class BotFrameworkHelper {

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
    
    public static String createImageJSON(def url = "", def alt = "") {
    
        def result = ""
        
        try{
            
            def contentType = "image/"
            def extension = url.split("\\.")[-1]
            
            if (extension == "jpg") {
                contentType += "jpeg"
            } else {
                contentType += extension
            }

            def image = [
                'contentType' : contentType,
                'contentUrl': url
            ]
            
            result = new groovy.json.JsonBuilder(image).toString()
        }
        catch (Exception e){
            println "Error createImageJSON: " + e.getMessage()
        }
        
        return result
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
}