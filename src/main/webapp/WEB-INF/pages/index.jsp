<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script type="text/javascript" >
        $(document).ready(function(){
            //sendAjax();
        });
        function sendAjax() {
            $.ajax({
                url: "/apple",
                type: 'POST',
                dataType: 'json',
                data: "{\"fName\":\"hmkcode\",\"lName\":\"dnsv\"}",
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function(data) {
                    alert(data.fName + " " + data.lName);
                },
                error:function(data,status,er) {
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });
        }
        function uploadFile(blobFile) {
            var fd = new FormData();
            fd.append("filedata", blobFile);
            fd.append("filename", blobFile.name);
            $.ajax({
                url: "/upload",
                type: "POST",
                data: fd,
                processData: false,
                contentType: false,
                success: function(response) {
                    alert(response);
                },
                error: function(jqXHR, textStatus, errorMessage) {
                    alert(errorMessage); // Optional
                }
            });
        }
        function sendFile(){
            //alert($("#upl")[0].files[0].name);
            uploadFile($("#upl")[0].files[0]);
        }
    </script>
</head>
<body>
<table>
    <tr><td>Name: </td><td><input type="button" onclick="sendAjax();" value="txt1"/></td></tr>
    <tr><td><input type="file" id="upl"/></td><td><input type="button" onclick="sendFile();" value="txt1"/></td></tr>
    <tr><td></td><td></td></tr>
</table>
</body>
</html>