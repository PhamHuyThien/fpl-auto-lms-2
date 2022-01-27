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
    <version>2.0.1</version>
</dependency>
```

### Danh sách args

Hiện thị trình duyệt:

```cmd
--show-browser
```  

Bật full màn hình trình duyệt:

```cmd
--full-screen
```

Bật hiển thị hình ảnh:

```cmd
--show-image
```

Bật chế độ automation driver:

```cmd
--enable-automation
```

Tắt tính năng theo dõi người dùng:

```cmd
--disable-analysis
```

### Cách sử dụng args

Khởi động ứng dụng bằng lệnh CMD:

```cmd
java -jar FPLautoLms_v2.jar -sb -fs -si
```

## Lưu ý:

Để cải thiện sản phẩm mặc định chúng tôi theo dõi người dùng.  
Để tắt tính năng này hãy sử dụng `--disable-analysis`.  
Nếu tắt tính năng theo dõi người dùng đồng nghĩa với việc sẽ mất kết nối hoàn toàn tới server (bao gồm các chức năng mở rộng hỗ trợ đính kèm sẽ không được hỗ trợ).

## Cộng đồng

Chúng tôi luôn tìm kiếm những người đóng góp, bạn có thể đóng góp, thắc mắc:

- [Trang thảo luận](https://www.facebook.com/210874576940463)
- [Tác giả](https://fb.com/thiendz.systemerror)