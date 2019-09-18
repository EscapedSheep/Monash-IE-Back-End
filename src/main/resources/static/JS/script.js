var divElement = document.getElementById('viz1568515689024');                    
var vizElement = divElement.getElementsByTagName('object')[0];                    
vizElement.style.width='90%';
vizElement.style.height=(divElement.offsetWidth*0.5) +'px';  

var scriptElement = document.createElement('script');                    
scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';                    
vizElement.parentNode.insertBefore(scriptElement, vizElement);