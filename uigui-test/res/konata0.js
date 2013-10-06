(function(ui, env, arg) {
	
	var PHI = (1+Math.sqrt(5))/2;
	
	var REFRESH_STATE = "REFRESH_STATE";
	
	var STATE_MAIN = "STATE_MAIN";
	var STATE_MENU = "STATE_MENU";
	
	var state = STATE_MAIN;

	var button = function(img, size, newState){
		var gap = Math.round(size*(1-1/PHI)/2);
		var cursor_id = ui.uniqueString();
		
		var child_touch = {
			x0:0, x1:size,
			y0:0, y1:size,
			cursor: true,
			cursor_id: cursor_id,
			cursor_click: function(){
				state = newState;
				ui.refresh(REFRESH_STATE);
			},
        };
		var child_img = {
        	x0:gap, x1:size-gap,
        	y0:gap, y1:size-gap,
        	u0:0, u1:1,
        	v0:0, v1:1,
        	refresh : [cursor_id],
        	get alpha(){
        		return (ui.cursorState(cursor_id)=="down")?1:(1/PHI);
        	} ,
        	img: img,
        };
 
		return {child:[child_touch, child_img]};
	}

	var main_page = {
		get condition() {
			return state == STATE_MAIN;
		},
		refresh : [REFRESH_STATE],
		child : [
        	menu_btn("res/menu_btn.png",Math.round(10*env.mm),STATE_MENU),
		],
	};
	
	var menu_page = {
		get condition() {
			return state == STATE_MENU;
		},
		refresh : [REFRESH_STATE],
		child : [
        	menu_btn("res/back_btn.png",Math.round(10*env.mm),STATE_MAIN),
		],
	};

	var ret = {
		clear_color : "#ff7f7f7f",
		child : [
	        main_page,
	        menu_page,
        ],
	};

	return ret;
})
