package com.example.update_apk_uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//public class MainActivity extends Activity {
//
//    private static final String TAG = "MainActivity";
//    private static final String APK_FILE_URL = "http://192.168.0.100:5000/apk_download";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Download APK file
//        new DownloadApkTask().execute(APK_FILE_URL);
//    }
//
//    private class DownloadApkTask extends AsyncTask<String, Void, Void> {
//
//        @Override
//        protected Void doInBackground(String... urls) {
//            String apkFileUrl = urls[0];
//            try {
//                URL url = new URL(apkFileUrl);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.connect();
//
//                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    // Save APK file to app-specific directory
//                    File apkDirectory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//                    File apkFile = new File(apkDirectory, "downloaded_app.apk");
//                    try (InputStream inputStream = connection.getInputStream();
//                         OutputStream outputStream = new FileOutputStream(apkFile)) {
//                        byte[] buffer = new byte[4096];
//                        int bytesRead;
//                        while ((bytesRead = inputStream.read(buffer)) != -1) {
//                            outputStream.write(buffer, 0, bytesRead);
//                        }
//                    }
//                    Log.d(TAG, "APK file downloaded to: " + apkFile.getAbsolutePath());
//                } else {
//                    Log.e(TAG, "Error downloading APK file: " + connection.getResponseMessage());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//
//}

public class MainActivity extends AppCompatActivity {

    private static final String APK_DOWNLOAD_URL = "link here ";

    private static final String APK_FILE_NAME = "DigiPick.apk";
//  //  private static final String APK_DOWNLOAD_LINK = " link here";
//
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;


    private Button downloadButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDownloadDialog();
            }
        });
    }
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
////                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
////            } else {
////                initDownloadManager();
////            }
////        } else {
////            initDownloadManager();
////        }
//
////    private void initDownloadManager() {
////        // Enable cookies to authenticate if necessary (optional)
////        CookieManager.getInstance().setAcceptCookie(true);
////
////        // Create a DownloadListener to handle the download request
////        DownloadListener downloadListener = new DownloadListener() {
////            @Override
////            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
////                startDownload(url, contentDisposition, mimeType);
////            }
////        };
////
////        // Set the DownloadListener to your WebView or any other view that might handle downloads
////        // webView.setDownloadListener(downloadListener);
////    }
////
////    private void startDownload(String url, String contentDisposition, String mimeType) {
////        try {
////            // Create a request for download
////            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
////            request.setMimeType(mimeType);
////            String fileName = URLUtil.guessFileName(url, contentDisposition, mimeType);
////            request.setTitle(fileName);
////            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
////            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
////
////            // Get the download service and enqueue the request
////            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
////            if (downloadManager != null) {
////                downloadManager.enqueue(request);
////                Toast.makeText(this, "Downloading APK...", Toast.LENGTH_SHORT).show();
////            } else {
////                Toast.makeText(this, "Download Manager not available", Toast.LENGTH_SHORT).show();
////            }
////        } catch (Exception e) {
////            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
////            e.printStackTrace();
////        }
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                initDownloadManager();
////            } else {
////                Toast.makeText(this, "Permission Denied: Cannot download APK.", Toast.LENGTH_SHORT).show();
////            }
////        }
////    }
////}
//
//
    private void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Download APK");
        builder.setMessage("Do you want to download the APK?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                CookieManager.getInstance().setAcceptCookie(true);
//                startDownload();
                installDownloadedApk();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

//    private void startDownload() {
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(APK_DOWNLOAD_URL));
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, APK_FILE_NAME);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//
//        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//        long downloadId = downloadManager.enqueue(request);
//
//        showProgressDialog(downloadId);
//    }
//
//    private void showProgressDialog(final long downloadId) {
//        final Dialog progressDialog = new Dialog(this);
//      //  progressDialog.setContentView(R.layout.layout_progress_dialog);
//        progressDialog.setCancelable(false);
//        progressDialog.setTitle("Downloading APK");
//        progressDialog.show();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                boolean downloading = true;
//                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                while (downloading) {
//                    DownloadManager.Query q = new DownloadManager.Query();
//                    q.setFilterById(downloadId);
//                    android.database.Cursor cursor = downloadManager.query(q);
//                    if (cursor.moveToFirst()) {
//                        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
//                        int status = cursor.getInt(columnIndex);
//                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
//                            progressDialog.dismiss();
//                            installDownloadedApk();
//                            downloading = false;
//                        } else if (status == DownloadManager.STATUS_FAILED) {
//                            progressDialog.dismiss();
//                           // Toast.makeText(MainActivity.this, "Download failed", Toast.LENGTH_SHORT).show();
//                            downloading = false;
//                        } else if (status == DownloadManager.STATUS_PAUSED) {
//                            // Handle paused status
//                        } else if (status == DownloadManager.STATUS_RUNNING) {
//                            int progressIndex = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
//                            int totalIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
//                            final long progress = cursor.getLong(progressIndex);
//                            final long total = cursor.getLong(totalIndex);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    // Update the progress bar
//                                    updateProgressBar(progress, total);
//                                }
//                            });
//                        }
//                    }
//                    cursor.close();
//                }
//            }
//        }).start();
//    }
//
//    private void updateProgressBar(long current, long total) {
//        // Calculate and update the progress percentage
//        int progress = (int) (current * 100 / total);
//        // Update your progress bar here
//        // For example, if you have a ProgressBar with id progressBar:
//        // ProgressBar progressBar = findViewById(R.id.progressBar);
//        // progressBar.setProgress(progress);
//    }

    private void installDownloadedApk() {
//        final Dialog progressDialog = new Dialog(this);
//        //  progressDialog.setContentView(R.layout.layout_progress_dialog);
//        progressDialog.setCancelable(false);
//        progressDialog.setTitle("Downloading APK");
//        progressDialog.show();
        // Your code to install the downloaded APK goes here
        // Use the existing implementation from the ApkDownloader class
        ApkDownloader apkDownloader = new ApkDownloader(MainActivity.this);
        apkDownloader.installDownloadedApk();
        apkDownloader.downloadApkFromUrl(APK_DOWNLOAD_URL);

    }


}


