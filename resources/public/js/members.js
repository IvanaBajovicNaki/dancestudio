$( document ).ready(function() {
  $.ajaxSetup({ cache: false });
  $(".deleteMember").click(deleteMember);
  $(".insertMember").click(insertMember);
  $(".updateMember").click(updateMember);
});

function deleteMember(){
  var closestTr = $(this).closest('tr');
  var memberId = $(closestTr).children()[0].innerText;
  $.ajax({
    url: '/remove-member',
    type: 'DELETE',
    data: {id: memberId},
    success: function(result) {
         window.location.href = "/member";
    }
});
};

function insertMember(){
  var id = $('#memberId').val();
  var name = $('#memberName').val();
  var surname = $('#memberSurname').val();
  var selectedGymnastichall = $('#gymnastichalls :selected').val();
  var selectedDanceprogram = $('#danceprograms :selected').val();
  var lesonsnumbermonth = $('#memberLesonsnumbermonth').val();
  var coachcommitment = $('#memberCoachcommitment').val();
  if (!id || id == '' || isNaN(id)) alert("Id must be integer!");
  else if (!lesonsnumbermonth || lesonsnumbermonth == '' || isNaN(lesonsnumbermonth)) alert("Number of lesons must be integer in range: 1-30!");
  else if(!coachcommitment || coachcommitment == '' || isNaN(coachcommitment)) alert("Coach's commitment must be integer in range: 1-100!");
  else{
	if(lesonsnumbermonth % 1 !== 0) alert("Number of lesons must be integer in range: 1-30!");
	else if(coachcommitment % 1 !== 0) alert("Coach's commitment must be integer in range: 1-100!");
	else{
  $.ajax({
    url: '/save-member',
    type: 'POST',
    data: {id: id,
           name: name,
           surname: surname,
           gymnastichall_id: selectedGymnastichall,
           danceprogram_id: selectedDanceprogram,
		   lesonsnumbermonth: lesonsnumbermonth,
		   coachcommitment: coachcommitment
          },
    success: function(result) {
       window.location.href = "/member";
    }
 });}}};


function updateMember(){
  var closestTr = $(this).closest('tr');
  var id = $(closestTr).children()[0].innerText;
  var lesonsnumbermonth = $(closestTr).children()[5].innerText;
  var coachcommitment = $(closestTr).children()[6].innerText;
  var l = parseFloat(lesonsnumbermonth);
  var x = parseFloat(coachcommitment);
  var y = 100;
  var c = (x/y);
   $.ajax({
    url: '/update-member',
    type: 'POST',
    data: {id:id,
		   lesonsnumbermonth: l,
           coachcommitment: c
          },
    success: function(result) {
        window.location.href = "/member";
    }
});
};

