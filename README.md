apis:

upload a file: /upload
get a file by id: /files/{fileName}
get a bunch of file with a criteria id > given id: /files/criteria/{id}
get a file's meta data by id: /meta/{id}

will throw MyFileNotFoundException if the given requested file is not exists. 
the file store in the local server and retrieve the file if user request.
the file's meta data will store in h2 database. 
when upload a file, the service will return the the DTO object which contains the url to get the requested resources.
