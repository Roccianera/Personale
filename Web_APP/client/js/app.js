// const { Console } = require("console");


let $content =$("main .content");












let  main  = () =>{


    "user strict";

    let $tabs = $(".tabs a span");  // COLLEZIONE DI OGGETTI DI QUESTO TIPO 

    // CONVERSIONE IN ARRAY 

    $tabs.toArray().forEach( tab =>{

        let $tab =$(tab);


        $tab.on("click", () =>{

            $tabs.removeClass("active");
            $tab.addClass("active");
            $("main .content").empty();


            if($tab.hasClass("FirstTab"))
                firstTab();            
            else if($tab.hasClass("SecondTab"))
                secondTab();
        
            else if($tab.hasClass("ThirdTab"))
                thirdTab();

            return false ; // se non inserisco questo istruzione segue il link ""

                            
        });
                
    } );









    $(".FirstTab").trigger("click");

}         






$(()=> {
    /* 
        LA PRIMA COSA CHE LO SCRIPT FARA SARA QUESTA OPERAZIONE DI CARICAMENTO 
        EQUIVALENTE AL $(document).ready();

    */


    main();
    

});






let firstTab = () =>{

 
}


let secondTab = () =>{


}
let thirdTab = () =>{

    try {


        let $input_label = $("<p>").text("Add word"),
        $input =$("<input>").addClass("description"),
        $button = $("<button>").text("+");


    $button.on("click", () => {

        let requestURL = "http://api.flickr.com/services/feeds/photos_public.gne?tags=";
        let requestURL2="&format=json&jsoncallback=?";

        let tag = $input.val();
        
        requestURL = requestURL+tag+requestURL2;

        $.getJSON(requestURL, flickerRespone => {


            console.log(flickerRespone);
            /*
            flickerRespone.item.forEach( item  => {

                


            });
            */
        });


        $input.val("");

    });




    let $sub_content =$("<div>").append($input_label)
                                .append($input)
                                .append($button);



    $content.append($sub_content);

        
    } catch (error) {

        console.log(error);
        
    }


}


