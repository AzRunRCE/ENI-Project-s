/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// anonyme
var Song = function (fileName, title, Artist) {
    this.fileName = fileName;
    this.title = title;
    this.artist = Artist;
};

var playlist = [
    new Song("Blue_Skies.mp3", "Blue Skies", "Silent Partner"),
    new Song("Cartoon_Hoedown.mp3", "Cartoon Hoedown", "Media Right Productions"),
    new Song("Earthy_Crust.mp3", "Earthy Crust", "Jungle Punks"),
    new Song("Hold_On_a_Minute.mp3", "Hold On a Minute", "Silent Partner"),
    new Song("Stay_With_You.mp3", "Stay With You", "Silent Partner"),
    new Song("Symphony_No_5_by_Beethoven.mp3", "Symphony No 5 by Beethoven", "Beethoven")
];
var videos = [
    "small.mp4"
]
var audio = null;
var video = null;
document.addEventListener("DOMContentLoaded", function (event) {
    audio = document.getElementById("audio");
    video = document.getElementById("video-player");
    $("#video-player").hide();
    $("#videos-view").hide();
    playSong(playlist[0]);
    playlist.forEach(function (song, index) {
        $("#musics-view").append('<li class="col-6 col-md-4 col-lg-3"><div id="' + index + '"class="card audio_item"><img class="card-img-top" src="pochettes/' + index + '.jpg" alt="Titre du morceau"> <div class="card-body"><h3 class="card-title">' + song.title + '</h3><h4 class="card-text">' + song.artist + '</h4> </div></div></li>');
    });
    videos.forEach(function (video, index) {
        $("#videos-view").append('<li class="col-6 col-md-4 col-lg-3"><div id="' + index + '"class="card video_item"><img class="card-img-top" src="pochettes/' + index + '.jpg" alt="Titre du morceau"> <div class="card-body"><h3 class="card-title">' + video + '</h3><h4 class="card-text">' + "" + '</h4> </div></div></li>');
    });
    audio.onended = function () {
        getNextTrack();
    };
    var search = $("#search-criteria");
    var items = $(".card");

    $("#search-criteria").keyup(function (e) {

        var v = search.val().toLowerCase();
        if (v == "") {
            items.show();
            return;
        }
        $.each(items, function () {
            var it = $(this);
            var lb = it.find("h3, h4").text().toLowerCase();
            if (lb.indexOf(v) == -1)
                it.hide();
        });
    });


    $("#list-group-item-music").click(function () {
        $("#video-player").hide();
        $("#videos-view").hide();
        $("#musics-view").show();
        video.pause();
    });
    $("#list-group-item-video").click(function () {
        $("#musics-view").hide();
        $("#videos-view").show();
        $("#video-player").show();
        audio.pause();
    });
    $(".audio_item").click(function () {
        var div_id = $(this).attr("id"); // gives you the ID of the clicked div
        playSong(playlist[parseInt(div_id, 0)]);
    });

    $(".video_item").click(function () {
        var div_id = $(this).attr("id"); // gives you the ID of the clicked div
        playVideo(videos[parseInt(div_id, 0)]);
    });
    $("#btn-volume-range").change(function () {
        audio.volume = $("#btn-volume-range").val();
    });

    $("#btn-volume-down").click(function () {
        if ($("#btn-volume-range").val() > 0.0) {
            $("#btn-volume-range").val(parseFloat($("#btn-volume-range").val(), 0.5) - 0.1);
            $("#btn-volume-range").change();
        }
    });
    $("#btn-volume-up").click(function () {
        if ($("#btn-volume-range").val() < 1.0) {
            $("#btn-volume-range").val(parseFloat($("#btn-volume-range").val(), 0.5) + 0.1);
            $("#btn-volume-range").change();
        }
    });
    $("#btn-control-center").click(function () {
        ((audio.paused) ? audio.play() : audio.pause());
        ((audio.paused) ? $("#icn-control-center").attr('class', 'fa fa-play-circle') : $("#icn-control-center").attr('class', 'fa fa-pause-circle'));
    });

    $("#btn-control-right").click(function () {
        getNextTrack();
    });



    $("#btn-control-left").click(function () {
        getPreviousTrack();
    });

    audio.addEventListener('loadedmetadata', function () {
        $("#btn-current-time").attr("max", audio.duration);
    });
    video.addEventListener('loadedmetadata', function () {
        $("#btn-current-time").attr("max", video.duration);
    });
    setInterval(function () {
        if ($("#video-player").is(':visible'))
        {
            $("#btn-current-time").val(video.currentTime);
        } else
        {
            $("#btn-current-time").val(audio.currentTime);
        }
    }, 10);
});


function getNextTrack()
{
    var song = document.getElementById("mp3_src").src;
    song = song.substring(song.lastIndexOf('/') + 1);
    var index = playlist.findIndex(x => x.fileName == song);
    var data = null;
    if (index === playlist.length - 1)
    {
        data = playlist[0];
    } else
    {
        data = playlist[index + 1];
    }
    playSong(data);
}


function getPreviousTrack()
{
    var song = document.getElementById("mp3_src").src;
    song = song.substring(song.lastIndexOf('/') + 1);
    var index = playlist.findIndex(x => x.fileName == song);

    var data = null;
    if (index === 0)
    {
        data = playlist[playlist.length - 1];
    } else
    {
        data = playlist[index - 1];
    }
    playSong(data);
}

function playSong(data)
{

    document.getElementById("mp3_src").src = "mp3/" + data.fileName;
    $("#lbl-artist-name").html(data.artist);
    $("#lbl-song-name").html(data.title);
    audio.load();
}

function playVideo(data)
{

    document.getElementById("mp4_src").src = "video/" + data;
    $("#lbl-artist-name").html("");
    $("#lbl-song-name").html(data);
    video.load();
}