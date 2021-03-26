import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:gameboard_front/domain/entities/GameSheet.dart';
import 'package:gameboard_front/domain/services/AssetService.dart';
import 'package:youtube_player_iframe/youtube_player_iframe.dart';

class MediaPage extends StatefulWidget {
  MediaPage({Key key, this.gameSheet, this.title}) : super(key: key);

  final GameSheet gameSheet;
  final String title;

  @override
  _MediaPageState createState() => _MediaPageState();
}

class _MediaPageState extends State<MediaPage> {
  AssetService assetService = AssetService();

  var session = null;

  List assets = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getAssets();
  }

  getAssets() {
    Iterable list = null;
    assetService.fetchAssetsByGameId(widget.gameSheet.id).then((response) {
      list = json.decode(response.body);

      setState(() {
        assets = list.toList();
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    var screenSize = MediaQuery.of(context).size;
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: SizedBox(
          width: (screenSize.width * 80) / 100,
          height: (screenSize.height * 80) / 100,
          child: Column(
            children: [
              Padding(
                padding: const EdgeInsets.all(12.0),
                child: Text(widget.gameSheet.title),
              ),
              Expanded(
                child: Center(
                  child: Container(
                    child: ListView.separated(
                      separatorBuilder: (context, index) =>
                          SizedBox(height: 18),
                      itemCount: assets.length,
                      itemBuilder: (context, index) {
                        return buildAssetContainer(assets[index]);
                      },
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget buildAssetContainer(String asset) {
    YoutubePlayerController _controller = YoutubePlayerController(
      initialVideoId: asset,
      params: YoutubePlayerParams(
        playlist: [], // Defining custom playlist
        startAt: Duration(seconds: 30),
        showControls: true,
        showFullscreenButton: true,
      ),
    );

    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        if (assets.length == 0) {
          return SizedBox(
            width: (constraints.maxWidth * 50) / 100,
            height: (constraints.maxHeight * 50) / 100,
            child: Text(
              "no trailer or picture for this game yet",
              textAlign: TextAlign.center,
            ),
          );
        }
        return SizedBox(
          width: (constraints.maxWidth * 50) / 100,
          child: Container(
            width: (constraints.maxHeight * 50) / 100,
            child: AspectRatio(
              aspectRatio: 16 / 9,
              child: YoutubePlayerIFrame(
                controller: _controller,
                aspectRatio: 16 / 9,
              ),
            ),
            decoration: BoxDecoration(
              shape: BoxShape.rectangle,
              color: Colors.blue,
              borderRadius: BorderRadius.circular(5),
            ),
          ),
        );
      },
    );
  }
}

/*
Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(12.0),
            child: Text(widget.gameSheet.title),
          ),
          Expanded(
            child: Center(
              child: Container(
                child: ListView.separated(
                  separatorBuilder: (context, index) => SizedBox(height: 18),
                  itemCount: assets.length,
                  itemBuilder: (context, index) {
                    return buildAssetContainer(assets[index]);
                  },
                ),
              ),
            ),
          ),
        ],
      )
*/

// class VideoPlayerScreen extends StatefulWidget {
//   VideoPlayerScreen({Key key, this.videoLink}) : super(key: key);

//   final String videoLink;

//   @override
//   _VideoPlayerScreenState createState() => _VideoPlayerScreenState();
// }

// class _VideoPlayerScreenState extends State<VideoPlayerScreen> {
//   VideoPlayerController _controller;
//   Future<void> _initializeVideoPlayerFuture;

//   @override
//   void initState() {
//     // Create an store the VideoPlayerController. The VideoPlayerController
//     // offers several different constructors to play videos from assets, files,
//     // or the internet.
//     _controller = VideoPlayerController.network(
//       widget.videoLink,
//     );

//     _initializeVideoPlayerFuture = _controller.initialize();

//     super.initState();
//   }

//   @override
//   void dispose() {
//     // Ensure disposing of the VideoPlayerController to free up resources.
//     _controller.dispose();

//     super.dispose();
//   }

//   @override
//   Widget build(BuildContext context) {
//     // Complete the code in the next step.
//     return FutureBuilder(
//       future: _initializeVideoPlayerFuture,
//       builder: (context, snapshot) {
//         if (snapshot.connectionState == ConnectionState.done) {
//           // If the VideoPlayerController has finished initialization, use
//           // the data it provides to limit the aspect ratio of the VideoPlayer.
//           return AspectRatio(
//             aspectRatio: _controller.value.aspectRatio,
//             // Use the VideoPlayer widget to display the video.
//             child: VideoPlayer(_controller),
//           );
//         } else {
//           // If the VideoPlayerController is still initializing, show a
//           // loading spinner.
//           return Center(child: CircularProgressIndicator());
//         }
//       },
//     );
//   }
// }
