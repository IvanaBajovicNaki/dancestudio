$(".deleteGymnastichall").click(function (){
var closestTr = $(this).closest('tr');
var gymnastichallId = $(closestTr).children()[0].innerText;
  $.ajax({
    url: '/remove-gymnastichall',
    type: 'DELETE',
    data: {id: gymnastichallId},
    success: function(result) {
         window.location.href = "/gymnastichall";
    }
});
});

$(".updateGymnastichall").click(function (){
  var closestTr = $(this).closest('tr');
  var gymnastichallId = $(closestTr).children()[0].innerText;
  var gymnastichallName = $(closestTr).children()[1].innerText;
   $.ajax({
    url: '/update-gymnastichall',
    type: 'POST',
    data: {id: gymnastichallId,
           name: gymnastichallName
          },
    success: function(result) {
        window.location.href = "/gymnastichall";
    }
});
});
