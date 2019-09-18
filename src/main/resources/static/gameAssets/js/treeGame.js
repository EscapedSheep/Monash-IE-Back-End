//set game window size
//var width = 1080;
var width = window.innerWidth *0.8;
//var height = 680;
var height = window.innerHeight*0.8;

//#gmae is the id of html elements where to create game
var game = new Phaser.Game(width, height, Phaser.AUTO, 'game');

var left;
var right;
//four states of game
var states = {
    preload: function() {

        this.preload = function () {
            game.stage.backgroundColor = '#000000';
            // load game resource
            game.load.crossOrigin = 'anonymous';
            game.load.image('treeImg', 'gameAssets/gameImg/tree1.png');
            game.load.image('sun', 'gameAssets/gameImg/sun2.png');
            game.load.image('sunImg','gameAssets/gameImg/sun.png');
            game.load.image('cloud', 'gameAssets/gameImg/cloud.png');
            game.load.image('worm', 'gameAssets/gameImg/worm.png');
            game.load.image('axe', 'gameAssets/gameImg/axe.png');
            game.load.image('steam', 'gameAssets/gameImg/think.png');
            game.load.image('cig', 'gameAssets/gameImg/cig.png');
            game.load.image('farm','gameAssets/gameImg/bg.jpg');
            game.load.image('fireImg','gameAssets/gameImg/fire-0.png');
            game.load.image('water','gameAssets/gameImg/water.png');
            game.load.atlasJSONHash('tree','gameAssets/gameImg/tree.png', 'gameAssets/gameImg/tree.json');
            game.load.atlasJSONHash('fire','gameAssets/gameImg/fire.png', 'gameAssets/gameImg/fire.json');
            game.load.spritesheet('rain', 'gameAssets/gameImg/rain.png', 17, 17);
            //loading progress
            var progressText = game.add.text(game.world.centerX, game.world.centerY, '0%', {
                fontSize: '60px',
                fill: '#ffffff'
            });
            progressText.anchor.setTo(0.5, 0.5);
            game.load.onFileComplete.add(function (progress) {
                progressText.text = 'Loading:' + progress + '%';
            });
            game.load.onLoadComplete.add(function () {
                game.state.start('created');
            })
        }
        /*
                //present game element,and teach how to play
                this.create = function () {
                    var bg = game.add.image(0,0,'sky');
                    bg.width = game.width;
                    bg.height = game.height;

                    //var fire = game.add.sprite(300,200,'fire');
                    //fire.scale.set(0.1);
                    //var run = fire.animations.add('run');
                    //fire.animations.play('run',2,true);
                    var fontSet = {fontSize: '30px', fill: '#f2bb15', fontWeight: 'bold'};
                    var remind = game.add.text(game.world.centerX, game.world.centerY, 'Click here or Press enter to start', fontSet);

                    remind.anchor.set(0.5, 0.5);

                    var goodList = game.add.text(game.world.centerX, game.world.height * 0.1, 'This things will grow the tree', fontSet);
                    goodList.anchor.set(0.5,0.5);

                    var sun = game.add.image(10,game.world.height * 0.2,'sun');
                    sun.scale.set(0.2);

                    //var water = game.

                    var badList = game.add.text(game.world.centerX, game.world.centerY + 100,'This things will harm the tree',fontSet);
                    badList.anchor.set(0.5,0.5);

                    var worm = game.add.image(10,game.world.centerY + 130,'worm');
                    worm.scale.set(0.2);

                    game.input.onTap.add(function() {
                        game.state.start('created');
                    });

                    enter = game.input.keyboard.addKey(Phaser.Keyboard.ENTER);
                },

                this.update = function () {
                    if (enter.isDown) {
                        game.state.start('created');
                    }
                }

         */
    },
    created: function() {
        this.create = function() {


            var bg = game.add.image(0,0,'farm');
            bg.width = game.width;
            bg.height = game.height;

            var tree = game.add.sprite(game.world.width*0.7, game.world.height*0.75,'tree');
            var treeImg = game.cache.getImage('treeImg');
            tree.width = game.world.width * 0.1;
            tree.height = tree.width / treeImg.width * treeImg.height;
            tree.anchor.set(0.5,0.5);

            var remind = game.add.text(game.world.centerX, game.world.centerY - 50, 'Click to start \n' +
                'Control: Drag the tree or use arrow keys(←→)', {
                fontSize: '20px',
                fill: '#F9FF00'
            });
            remind.anchor.set(0.5,0.5);

            game.input.onTap.add(function() {
                game.state.start('play');
            });

            left = game.input.keyboard.addKey(Phaser.Keyboard.LEFT);
            right = game.input.keyboard.addKey(Phaser.Keyboard.RIGHT);
        },

            this.update = function () {
                if(left.isDown || right.isDown) {
                    game.state.start('play');
                }
            }
    },

    play: function() {
        var tree;
        var dropElement;
        var score = 0;
        var title;
        var emitter;
        var cloud;
        var sun;
        var basicWidth;
        var basicHeight;
        var fire;
        var rainTitle;
        var rainProgress = 0;
        var check = true;
        this.create = function() {
            score = 0;

            game.physics.startSystem(Phaser.Physics.Arcade);
            game.physics.arcade.gravity.y = 300;

            var bg = game.add.image(0,0,'farm');
            bg.width = game.world.width;
            bg.height = game.world.height;

            cloud = game.add.image(game.world.width * 0.3,10,'cloud');
            cloud.width = game.world.width * 0.2;
            var cloudImg = game.cache.getImage('cloud');
            cloud.height = cloud.width / cloudImg.width * cloudImg.height;
            cloud.visible=false;

            sun = game.add.image(game.world.width * 0.3,40,'sunImg');
            sun.width = game.world.width * 0.5;
            var sunImg = game.cache.getImage('sunImg');
            sun.height = sun.width / sunImg.width * sunImg.height;
            //sun.anchor.set(0.5,0.5);

            tree = game.add.sprite(game.world.width*0.7, game.world.height*0.75,'tree',0);
            //tree.frame = 1;
            //tree.animations.add('small',[1,1]);
            //tree.animations.add('big',[0,0]);
            //tree.animations.play('small',30,true);
            //tree.width = 40;
            //tree.height = 30;
            var treeImg = game.cache.getImage('treeImg');
            basicWidth = game.world.width * 0.1;
            basicHeight = basicWidth / treeImg.width * treeImg.height;
            tree.anchor.set(0.5,0.5);
            game.physics.enable(tree);
            tree.body.allowGravity = false;
            tree.body.collideWorldBounds = true;

            var touching = false;
            game.input.onDown.add(function(pointer) {
                if (Math.abs(pointer.x - tree.x) < tree.width / 2) touching = true;
            });
            game.input.onUp.add(function() {
                touching = false;
            });
            game.input.addMoveCallback(function(pointer, x, y, isTap) {
                if (!isTap && touching) tree.x = x;
            });

            //bounds = new Phaser.Rectangle(0,0, game.world.width, game.world.height);
            //tree.inputEnabled = true;
            //tree.input.enableDrag(false,true,true,100,bounds);
            //tree.input.boundsRect = bounds;
            //score
            title = game.add.text(0, game.world.height *0.1, 'Score: 0', {
                fontSize: '40px',
                fontWeight: 'bold',
                fill: '#F9FF00'
            });
            //title.anchor.set(0.5, 0.5);

            //tree movement control
            /*
            var touching = false;
            game.input.onDown.add(function(pointer) {

                if (Math.abs(pointer.x - tree.x) < tree.width / 2 && Math.abs(pointer.y - tree.y) < tree.height /2)
                    touching = true;
            });
            game.input.onUp.add(function() {
                touching = false;
            });
            game.input.addMoveCallback(function(pointer, x, y, isTap) {
                if (!isTap && touching) {
                    tree.x = x;
                    tree.y = y;
                }

            });
             */
            var rainImg =  game.add.image(5,5,'cloud');
            rainImg.scale.set(0.05);
            rainTitle = game.add.text(rainImg.x+20,rainImg.y,"0%",{
                font: "18px Arial",

            });
            //rainTitle.anchor.set(0.5,0.5);

            //Emitter for rain
            emitter = game.add.emitter(game.world.centerX, 0, 2000);
            emitter.width = game.world.width;
            emitter.makeParticles('rain',26,50,true,false);
            emitter.minParticleScale = 0.5;
            emitter.maxParticleScale = 1;
            //emitter.setScale(1, 1, 5, 5, 3000);
            emitter.setRotation(0,0);
            emitter.setYSpeed(300, 500);
            emitter.setXSpeed(-5, 5);

            dropElement = game.add.group();
            var elementTypes = ['sun', 'axe', 'worm', 'cig','sun','water'];
            var elementTimer = game.time.create(true);
            elementTimer.loop(950, function() {
                var x = Math.random() * game.world.width;
                var index = Math.floor(Math.random() * elementTypes.length);
                var type = elementTypes[index];
                var element = dropElement.create(x, 0, type);
                element.type = type;
                // physical movement enable
                game.physics.enable(element);
                // size
                var elementImg = game.cache.getImage(type);
                element.width = game.world.width / 20;
                element.height = element.width / elementImg.width * elementImg.height;
                //
                element.body.collideWorldBounds = true;
                element.body.onWorldBounds = new Phaser.Signal();
                element.body.onWorldBounds.add(function(element, up, down, left, right) {
                    if (down) {
                        element.kill();
                    }
                });
            });
            elementTimer.start();

            var fireImg = game.cache.getImage('fireImg');
            var fireTimer = game.time.create(true);
            fireTimer.loop(5000, function() {
                var x = Math.random() * game.world.width;
                fire = game.add.sprite(x,0,'fire');
                fire.animations.add('start');
                fire.animations.play('start',15,true);
                // physical movement enable
                game.physics.enable(fire);
                // size
                fire.width = game.world.width / 20;
                fire.height = fire.width / fireImg.width * fireImg.height;
                //
                fire.body.collideWorldBounds = true;
                fire.body.onWorldBounds = new Phaser.Signal();
                fire.body.onWorldBounds.add(function(fire, up, down, left, right) {
                    if (down) {
                        fire.kill();
                    }
                });
            });
            fireTimer.start();


        },

            this.update = function () {
                var treeImg = game.cache.getImage('treeImg');
                if (tree.width > 20 && tree.height > 20) {
                    tree.width = basicWidth + score*0.7;
                    //tree.height = basicHeight +score*0.7;
                    tree.height = tree.width / treeImg.width * treeImg.height;
                    //tree.scale.set(0.4+score*0.001);
                }

                if (score > 100 && score < 250) {
                    tree.frame = 1;
                }
                else if (score >= 250) {
                    tree.frame = 2;
                }
                if (check === true) {
                    game.physics.arcade.overlap(tree, fire, pickFire, null, this);
                }
                game.physics.arcade.overlap(emitter, tree,rainCount,null,this);
                game.physics.arcade.overlap(tree, dropElement,pickElement,null,this);
                if (left.isDown && tree.x >=0)
                {
                    tree.x -= 9;
                }
                else if (right.isDown && tree.x <= game.world.width)
                {
                    tree.x += 9;
                }

            }

        function rain() {
            cloud.visible = true;
            sun.visible=false;
            emitter.start(false,1300,15,0);
            game.time.events.add(3000, destoryEmitter, this);
            game.time.events.add(3000,destoryCloud,this);
            game.time.events.add(3000,showSun,this);
        }

        function destoryCloud() {
            cloud.visible = false;
        }

        function showSun() {
            sun.visible = true;
        }

        function rainCount(tree,emitter) {
            score += 1;
            title.text = 'Score:' + score;
            emitter.kill();
        }

        function destoryEmitter() {
            emitter.on = false;
        }

        function pickFire(tree, fire) {
            rainProgress -= 5;
            rainTitle.text = rainProgress + '%';
            score -= 10;
            title.text = 'Score: ' + score;
            var fireImg = game.add.image(tree.x,tree.y,'fire');
            fireImg.animations.add('start');
            fireImg.animations.play('start',15,true);
            fireImg.anchor.set(0.5,0.5);
            fireImg.scale.set(0.3);
            check = false;
            var showTween = game.add.tween(fireImg).to({
                alpha: 1,
                y: fireImg.y - 20
            }, 1000, Phaser.Easing.Linear.None, true, 0, 0, false);
            showTween.onComplete.add(function() {
                var hideTween = game.add.tween(fireImg).to({
                    alpha: 0,
                    y: fireImg.y - 20
                }, 300, Phaser.Easing.Linear.None, true, 200, 0, false);
                hideTween.onComplete.add(function() {
                    fireImg.kill();
                    check = true;
                });
            })

        }

        function pickElement(tree, element) {
            if (element.type === 'axe'){
                game.state.start('over', true, false, score);
            } else {
                var point = 1;
                var emoji = '😄';
                var steam = false;
                if (element.type === 'worm' || element.type === 'cig') {
                    point = -2;
                    emoji = '😭';
                } else if (element.type === 'sun' || element.type === 'water') {
                    point = 5;
                    steam = true;
                    rainProgress += 10;
                }

                if (steam === true) {
                    steam = game.add.image(tree.x, tree.y, 'steam');
                    //var goalImg = game.cache.getImage('steam');
                    steam.width = basicWidth/2;
                    steam.height = basicHeight/2;
                    steam.alpha = 0;
                    steam.anchor.set(1,1.5)

                    var showTween = game.add.tween(steam).to({
                        alpha: 1,
                        y: steam.y - 20
                    }, 100, Phaser.Easing.Linear.None, true, 0, 0, false);
                    showTween.onComplete.add(function() {
                        var hideTween = game.add.tween(goal).to({
                            alpha: 0,
                            y: steam.y - 20
                        }, 300, Phaser.Easing.Linear.None, true, 200, 0, false);
                        hideTween.onComplete.add(function() {
                            steam.kill();
                        });
                    })
                }

                var goal = game.add.text(tree.x, tree.y, emoji);
                //goal.width = tree.width;
                goal.alpha = 0;

                var showTween = game.add.tween(goal).to({
                    alpha: 1,
                    y: goal.y - 20
                }, 100, Phaser.Easing.Linear.None, true, 0, 0, false);
                showTween.onComplete.add(function() {
                    var hideTween = game.add.tween(goal).to({
                        alpha: 0,
                        y: goal.y - 20
                    }, 100, Phaser.Easing.Linear.None, true, 200, 0, false);
                    hideTween.onComplete.add(function() {
                        goal.kill();
                    });
                });
                score += point;
                title.text = 'Score: '+ score;
                element.kill();

                if (rainProgress >= 100) {
                    rain();
                    rainProgress -= 100;
                }
                rainTitle.text = rainProgress + '%';
            }
        }
    },

    over: function() {
        var score = 0;
        this.init = function() {
            score = arguments[0];
        }
        this.create = function() {
            // 添加背景
            var bg = game.add.image(0, 0, 'farm');
            bg.width = game.world.width;
            bg.height = game.world.height;

            var title = game.add.text(game.world.centerX, game.world.height * 0.25, 'Game over', {
                fontSize: '40px',
                fontWeight: 'bold',
                fill: '#F9FF00'
            });
            title.anchor.setTo(0.5, 0.5);
            var scoreStr = 'your score：'+score;
            var scoreText = game.add.text(game.world.centerX, game.world.height * 0.4, scoreStr, {
                fontSize: '30px',
                fontWeight: 'bold',
                fill: '#F9FF00'
            });
            scoreText.anchor.setTo(0.5, 0.5);
            var remind = game.add.text(game.world.centerX, game.world.height * 0.6, 'press to start again', {
                fontSize: '20px',
                fontWeight: 'bold',
                fill: '#F9FF00'
            });
            remind.anchor.setTo(0.5, 0.5);

            $("#score").text(score);

            game.input.onTap.add(function() {
                game.state.start('play');
            });
        }
    }
};


Object.keys(states).map(function(key) {
    game.state.add(key, states[key]);
});

game.state.start('preload');
