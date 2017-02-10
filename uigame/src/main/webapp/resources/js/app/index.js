$(document).ready(function() {
    var flag_user = 0;
	$("#pop_pup_1").hide();
	$("#user").click(function() {
		if(flag_user == 0) {
			$("#pop_pup_1").show();
			flag_user = 1;
		} else {
			$("#pop_pup_1").hide();
			flag_user = 0;
		}
	});
});