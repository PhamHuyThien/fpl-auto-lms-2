# FPL@utoLMS.2

[FPL@utoLMS](https://github.com/PhamHuyThien/fpl-auto-lms-2) là một công cụ miễn phí mã nguồn mở giúp các sĩ tử vượt qua
các bài tập trên hệ thống LMS của trường [FPT Polytechnic](https://caodang.fpt.edu.vn/) một cách dễ dàng nhất.  
Được phát triển dựa trên [FPL@utoLMS 1.0.7 core](https://github.com/PhamHuyThien/fpl-auto-lms), nâng cấp hiệu năng, hỗ
trợ đa nền tảng, maintain lại toàn bộ source code.

## Hướng dẫn sử dụng

Chi tiết cách cài đặt môi trường, khởi động ứng dụng, sử dụng phần mềm
... [xem tại đây](https://www.youtube.com/watch?v=TYhdLhFD3j8).

## Tải về

Jar FPL@utoLMS có sẵn trong [releases github](https://github.com/PhamHuyThien/fpl-auto-lms-2/releases).

## Dành cho nhà phát triển

### DriverDL library

Để có thể tự động tải về driver selenium phù hợp với thiết bị của bạn, chúng tôi đã sử dụng một thư viện tự phát triển
có tên [driver-dl](https://github.com/PhamHuyThien/driver-dl).

```xml

<dependency>
    <groupId>com.thiendz.lib</groupId>
    <artifactId>driver-dl</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Danh sách args debug selenium

Hiện thị trình duyệt: `-sb` hoặc `--show-browser`  
Bật full màn hình trình duyệt: `-fsc` hoặc `--full-screen`  
Bật hiển thị hình ảnh: `-si` hoặc `--show-image`  
Bật chế độ automation driver: `-ea` hoặc `--enable-automation`  
Ví dụ:

```cmd
java -jar FPLautoLms_v2.jar -sb -fsc -si
```

## Lưu ý

Để cải thiện sản phẩm, mặc định chúng tôi thu thập dữ liệu người dùng.  
Để tắt tính năng này vui lòng thêm args `-d` hoặc `--disable-analysis` khi khởi động ứng dụng.  
Ví du: khởi động ứng dụng bằng CMD trên hệ điều hành window:

```cmd
java -jar FPLautoLms.jar --disable-analysis
```

## Lich sử thay đổi

Lịch sử thay đổi qua từng phiên bản được đề cập
trong [change-log.md](https://github.com/PhamHuyThien/fpl-auto-lms-2/blob/master/change-log.md).


## Cộng đồng

Chúng tôi luôn tìm kiếm những người đóng góp, bạn có thể đóng góp, thắc mắc:

- [Trang thảo luận](https://www.facebook.com/210874576940463)
- [Tác giả](https://fb.com/thiendz.systemerror)