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

    console.log("First Tab!");
    

}


let secondTab = () =>{
    console.log("Second Tab!");

}
let thirdTab = () =>{

    console.log("Third Tab!");

}