$(document).ready(function (){

  $(".deleteDanceprogram").click(function (){
	var closestTr = $(this).closest('tr');
	var danceprogramId = $(closestTr).children()[0].innerText;
		$.ajax({
			url: '/remove-danceprogram',
			type: 'DELETE',
			data: {id: danceprogramId},
			success: function(result) {
				window.location.href = "/danceprogram";
			}
		});
	});

  $(".editable").dblclick(function (){
    var OriginalContent = $(this).text();
    $(this).addClass("cellEditing");
    $(this).html("<input type='text' value='" + OriginalContent + "' />");
    $(this).children().first().focus();
    $(this).children().first().keypress(function (e){
      if (e.which == 13){
        var newContent = $(this).val();
        $(this).parent().text(newContent);
        $(this).parent().removeClass("cellEditing"); } });

    $(this).children().first().blur(function(){
      $(this).parent().text(OriginalContent);
      $(this).parent().removeClass("cellEditing"); }); });
});


$(".updateDanceprogram").click(function (){
  var closestTr = $(this).closest('tr');
  var danceprogramId = $(closestTr).children()[0].innerText;
  var danceprogramName = $(closestTr).children()[1].innerText;
   $.ajax({
    url: '/update-danceprogram',
    type: 'POST',
    data: {id: danceprogramId,
           name: danceprogramName
          },
    success: function(result) {
        window.location.href = "/danceprogram";
    }
});
});
