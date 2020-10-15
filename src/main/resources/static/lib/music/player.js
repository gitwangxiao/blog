
	$(function(){
		var song = [
			{
				'cover' : 'https://p1.music.126.net/TXdH_qsA3L8Qlxt_acr4fw==/2412328511393200.jpg',
				'src' : '../../images/sl.mp3',
				'title' : '透明'
			}]

		var audioFn = audioPlay({
			song : song,
			autoPlay : true  //是否立即播放第一首，autoPlay为true且song为空，会alert文本提示并退出
		});

		/* 向歌单中添加新曲目，第二个参数true为新增后立即播放该曲目，false则不播放 */
		audioFn.newSong({
			'cover' : 'http://p1.music.126.net/i4v61weWROOUeQEzuQwIWg==/109951163923224490.jpg?param=200x200',
			'src' : 'http://m10.music.126.net/20200306114332/331774ff7c867627c908d240789cf66e/ymusic/4630/2e9a/e035/62d70cd638c8202abc17bd68998310c1.mp3',
			'title' : '十七岁'
		},false);

		/* 暂停播放 */
		//audioFn.stopAudio();

		/* 开启播放 */
		//audioFn.playAudio();

		/* 选择歌单中索引为3的曲目(索引是从0开始的)，第二个参数true立即播放该曲目，false则不播放 */
		//audioFn.selectMenu(3,true);

		/* 查看歌单中的曲目 */
		//console.log(audioFn.song);

		/* 当前播放曲目的对象 */
		//console.log(audioFn.audio);
	});