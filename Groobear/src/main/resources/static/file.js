var regex = new RegExp("(.*?)\\.(sh|alz)$"); //제외하고 싶은 확장자.
	var maxSize = 5242880;

	function checkExtension(fileName, fileSize) {
		if (fileSize >= maxSize) {
			alert('파일 사이즈 초과');
			return false;
		}
		if (regex.test(fileName)) {
			alert('해당 종류의 파일은 업로드할 수 없습니다.');
			return false;
		}
		return true;
	}

	 function showImage(fileCallPath) {
	/* 	$(".bigPictureWrapper").css("display", "flex").show();
		$(".bigPicture")
			.html("<img src='/display?fileName=" + encodeURI(fileCallPath) + "'>")
			.animate({width:'100%', height:'100%'}, 1000);
	}
	
	$(".bigPictureWrapper").on("click", function(e){
		$(".bigPicture").animate({width:'0%', height:'0%'}, 1000);
		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		}, 1000);
	}); */
	}

	

	function showUploadedFile(uploadResultArr) {
		var str = "";
		$(uploadResultArr).each(function(i, obj) {
			console.log(i, obj)
			if (!obj.image) {
				var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
				var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
				str += "<li><div><a href='/download?fileName=" + fileCallPath + "'>" +
					"<img src='img/attach.png/'>" + obj.fileName + "</a>" +
					"<span data-file=\'" + fileCallPath + "\' data-type='file'> x </span>" +
					"</div></li>";
			} else {
				var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
				var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
				originPath = originPath.replace(new RegExp(/\\/g), "/");
				str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\">" +
					"<img src='display?fileName=" + fileCallPath + "'></a>" +
					"<span data-file=\'" + fileCallPath + "\' data-type='image'> x </span>" +
					"</li>";
			}
		});
		uploadResult.append(str);
	}
	var uploadResult = $(".uploadResult ul");
	$(".uploadResult").on("click", "span", function(e) {
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		var targetLi = $(this).closest("li").remove();
		$.ajax({
			url: "/deleteFile",
			data: {fileName: targetFile, type: type},
			dataType: "text",
			type: "POST",
			success: function(result) {
				alert(result);
				targetLi.remove();
			}
		});
	});

	var cloneObj = $(".uploadDiv").clone();

	$("#uploadBtn").on("click", function(e) {
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		for (var i = 0; i < files.length; i++) {
			if (!checkExtension(files[i].name, files[i].size)) {
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		$.ajax({
			url: "/uploadAjaxAction",
			processData: false,
			contentType: false,
			data: formData,
			type: "POST",
			dataType: "json",
			success: function(result) {
				showUploadedFile(result);
				$(".uploadDiv").html(cloneObj.html());
			},
			error: function(error) {
				console.log(error);
			}
		});
	});