// mapbox access token
mapboxgl.accessToken = 'pk.eyJ1IjoiaGFtemFraGFuOTQiLCJhIjoiY2p6cGpwOWdlMHFmcjNibzVzcHd3d3NzMiJ9.QnYqiNYoM2Kvcwm00alhuw';

// building the map
var map = new mapboxgl.Map({container: 'map',style: 'mapbox://styles/mapbox/light-v10',
    center: [144.95404601277124, -37.79334526904956],
    zoom: 13});

// adding geocode to the map for search functionality
// restricting the geo encode to australia 
var geocoder = new MapboxGeocoder({
    accessToken: mapboxgl.accessToken,
    mapboxgl: mapboxgl,
    placeholder: "Try suburb",
    countries: 'au',
});
document.getElementById('geocoder').appendChild(geocoder.onAdd(map));

// checking the input from user in the search box
geocoder.on('result', function(ev) {
    // getting latitude and logitude from the search box
    var lat = ev.result.geometry.coordinates[1]
    var lon = ev.result.geometry.coordinates[0]
    console.log(lat,lon);
    // building url for api call to backend to get the data
    var url = "http://treepal.herokuapp.com/activity/findByLocation?lon="+lon+"&lat="+lat+"&lim=200"      
          
    $.get(url, function(result){
        if(result.code == 200)
        {
            
            // this means when the response comes back OK
            console.log("200 OK");
            var theData2 = {
              "type": "FeatureCollection", 
              "features": result.data
            }
            console.log(theData2);
            // checking if data is already added to the map
            var mapLayer = map.getLayer('location');
            if(typeof mapLayer !== 'undefined') 
            {
                // Remove map layer & source.
                map.removeLayer('location').removeSource('location');
            }
            
            // adding data to the map
            map.addLayer({
                id: 'location',
                type: 'symbol',
                // Add a GeoJSON source containing place coordinates and information.
                source: {
                  type: 'geojson',
                  data: theData2
                }
                });
            
            // building side list with the map
            buildLocationList(theData2);       
            console.log('build locations');
            
            theData2.features.forEach(function(marker) 
            {
                // Create a div element for the marker
                var el = document.createElement('div');
                
                // Add a class called 'marker' to each div
                el.className = 'marker';
                new mapboxgl.Marker(el, { offset: [0, 23] })
                .setLngLat(marker.geometry.coordinates)
                .addTo(map);
                
                // add event listener on click
                el.addEventListener('click', function(e) {
                var activeItem = document.getElementsByClassName('active');
                // 1. Fly to the point
                flyToStore(marker);
                // 2. Close all other popups and display popup for clicked store
                createPopUp(marker);
                // 3. Highlight listing in sidebar (and remove highlight for all other listings)
                e.stopPropagation();
                if (activeItem[0]) {
                  activeItem[0].classList.remove('active');
                }
                var listing = document.getElementById('listing-' + i);
                console.log(listing);
                listing.classList.add('active');
                });
            });   
        }
        else
        {
            // this is when the response is NOT ok
            alert("not ok :(");
        }          
        })          
      });                    
                
/////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////// Functions //////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

// function to build store locations
function buildLocationList(data) 
{
    // remove current listing
    var listings = document.getElementById('listings');
    listings.innerHTML = '';
    
    // Iterate through the list of trees
    for (i = 0; i < data.features.length; i++) 
    {
        // getting each feature
        var currentFeature = data.features[i];
        var prop = currentFeature.properties;
        
        // adding listing
        var listing = listings.appendChild(document.createElement('div'));
        listing.className = 'item';
        listing.id = 'listing-' + i;
        
        // Create a new link with the class 'title' for each store
        // and fill it with the store address

        var link = listing.appendChild(document.createElement('a'));
        link.href = '#';
        link.className = 'title';
        link.dataPosition = i;
        link.innerHTML = prop.common;
      
        // Create a new div with the class 'details' for each store
        // and fill it with the city and phone number
        var details = listing.appendChild(document.createElement('div'));
        
        details.innerHTML = '<b>'+ "Scientific Name: "+ '</b>'+ prop.scientific.toLowerCase();
        if (prop.source) 
        {
            details.innerHTML += '<br/>' + '<b>'+ "Location: "+ '</b>' + prop.source;
        }
        
        if (prop.location) 
        {
            details.innerHTML += ' - ' + prop.location;
        }
        
        if (prop.height) 
        {
            details.innerHTML +=  '<br/>' + '<b>'+ "Height: "+ '</b>' + prop.height;
        }
        
        if (prop.health) 
        {
            details.innerHTML +=  '<br/>' + '<b>'+ "Health: "+ '</b>'+ prop.health;
        }
        
        if (prop.maturity) 
        {
            details.innerHTML +=  '<br/>' + '<b>'+ "Age: "+ '</b>'  + prop.maturity;
        }
        

        // Add an event listener for the links in the sidebar listing
        link.addEventListener('click', function(e) {
        // Update the currentFeature to the store associated with the clicked link
        var clickedListing = data.features[this.dataPosition];
        // 1. Fly to the point associated with the clicked link
        flyToStore(clickedListing);
        // 2. Close all other popups and display popup for clicked store
        createPopUp(clickedListing);
        // 3. Highlight listing in sidebar (and remove highlight for all other listings)
        var activeItem = document.getElementsByClassName('active');
        if (activeItem[0]) 
        {
            activeItem[0].classList.remove('active');
        }
        this.parentNode.classList.add('active');
        });
    } 
}

// function to make the location fly on the map
function flyToStore(currentFeature) 
{
  map.flyTo({
    center: currentFeature.geometry.coordinates,
    zoom: 15
  });
}

// function to create the pop up on the map
function createPopUp(currentFeature) 
{
  console.log(currentFeature.properties);
  var popUps = document.getElementsByClassName('mapboxgl-popup');
  // Check if there is already a popup on the map and if so, remove it
  if (popUps[0]) popUps[0].remove();
  var popup = new mapboxgl.Popup({ closeOnClick: false })
    .setLngLat(currentFeature.geometry.coordinates)
    .setHTML('<h3>MyTreePal</h3>' +
      '<h4>' + currentFeature.properties.common + '</h4>')
    .addTo(map);
}