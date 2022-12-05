package com.ssafy.home.board.dto;

public class FileInfo {
    private String saveFolder, originalFile, saveFile;

    public FileInfo() {
    }

    public FileInfo(String saveFolder, String originalFile, String saveFile) {
        setSaveFolder(saveFolder);
        setOriginalFile(originalFile);
        setSaveFile(saveFile);
    }

    public String getSaveFolder() {
        return saveFolder;
    }

    public void setSaveFolder(String saveFolder) {
        if (saveFolder != null)
            this.saveFolder = saveFolder;
    }

    public String getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(String originalFile) {
        if (originalFile != null)
            this.originalFile = originalFile;
    }

    public String getSaveFile() {
        return saveFile;
    }

    public void setSaveFile(String saveFile) {
        if (saveFile != null)
            this.saveFile = saveFile;
    }
}
