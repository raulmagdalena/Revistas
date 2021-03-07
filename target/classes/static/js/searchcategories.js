// ajax jquery to auto populate categories form field
// TODO make this autocomplete function accept a parameter with the datasource to do it reusable
$( function(){
    function split( val ) {
        return val.split( /,\s*/ );
    }
    function extractLast( term ) {
        return split( term ).pop();
    }

    $("#categories")
        .on("keydown", function(event){
            if(event.keyCode === $.ui.keyCode.TAB && $(this).autocomplete("instance").menu.active){
                event.preventDefault();
                }
        })
        .autocomplete({
        source: function(request, response){
            $.getJSON("magazines/autocomplete",{
                term: extractLast(request.term)
                }, response);
            },
        minLength: 2,
        focus: function(){
            return false;
        },
        select: function(event, ui){
            var terms = split(this.value);
            terms.pop();
            terms.push(ui.item.value);
            terms.push("");
            this.value = terms.join(", ");
            return false;
        }
    });
});

