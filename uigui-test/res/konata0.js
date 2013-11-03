(function(ug, pal, arg) {
	
	var PHI = (1+Math.sqrt(5))/2;
	
	var REFRESH_STATE = "REFRESH_STATE";
	
	var STATE_MAIN = "STATE_MAIN";
	var STATE_MENU = "STATE_MENU";
	
	var state = STATE_MAIN;

	var menu_btn = function(img, size, newState){
		var gap = Math.round(size*(1-1/PHI)/2);
		var cursorId = ug.uniqueString();
		
		var childTouch = {
			x0:0, x1:size,
			y0:0, y1:size,
			cursor: true,
			cursorId: cursorId,
			cursorClick: function(){
				state = newState;
				ug.refresh(REFRESH_STATE);
			},
        };
		var childImg = {
        	x0:gap, x1:size-gap,
        	y0:gap, y1:size-gap,
        	u0:0, u1:1,
        	v0:0, v1:1,
        	refresh : [cursorId],
        	get alpha(){
        		return (ug.cursorState(cursorId)=="down")?1:(1/PHI);
        	} ,
        	img: img,
        };
 
		return {child:[childTouch, childImg]};
	}

	var mainPage = {
		get enable() {
			return state == STATE_MAIN;
		},
		refresh : [REFRESH_STATE],
		child : [
        	menu_btn("res/menu_btn.png",Math.round(10*pal.mm),STATE_MENU),
		],
	};
	
	var menuPage = {
		get enable() {
			return state == STATE_MENU;
		},
		refresh : [REFRESH_STATE],
		child : [
        	menu_btn("res/back_btn.png",Math.round(10*pal.mm),STATE_MAIN),
		],
	};

	var ret = {
		clearColor : "#ff7f7f7f",
		child : [
	        mainPage,
	        menuPage,
        ],
	};

	return ret;
})
