<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Multiple File Upload</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</head>
<body>
<h1>Spring Multiple File Upload example</h1>
 
<form:form method="post" action="save.html"
        modelAttribute="uploadForm" enctype="multipart/form-data">
 
    <p>Select files to upload. Press Add button to add more file inputs.</p>
 
    <input id="addFile" type="button" value="Add File" />
    <table id="fileTable">
        <tr>
            <td><input name="files[0]" type="file" /></td>
        </tr>
    </table>
    <br/><input type="submit" value="Upload" />
</form:form>
<script type="text/javascript" src="js/fileupload/FileUpload.js"></script>
</body>