package Objects.model;

public class Objects_Versions {
    int id_Version;
    int file_Version;
    int fileId;
    String fileName;
    String fileType;
    byte[] fileData;
    long fileSize;
    String fileDate;
    String fileUri;
    String fileUsername;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileUsername() {
        return fileUsername;
    }

    public void setFileUsername(String fileUsername) {
        this.fileUsername = fileUsername;
    }

    public int getId_Version() {
        return id_Version;
    }

    public void setId_Version(int id_Version) {
        this.id_Version = id_Version;
    }

    public int getFile_Version() {
        return file_Version;
    }

    public void setFile_Version(int file_Version) {
        this.file_Version = file_Version;
    }
}
