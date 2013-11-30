(function(ug, pal, arg) {
	
	var PHI = (1+Math.sqrt(5))/2;

	var minSize = function(){ return Math.min(pal.width,pal.height); };
	var imgSize = function(){ return minSize()/PHI; };
	
	return {
		clearColor: "#ff7f7f7f",
		child: [{
		        img: "data/libgdx.png",
		        get x0(){return Math.round((pal.width-imgSize())/2);},
		        get x1(){return Math.round((pal.width+imgSize())/2);},
		        get y0(){return Math.round((pal.height-imgSize())/2);},
		        get y1(){return Math.round((pal.height+imgSize())/2);},
        }],
	};
	
})
