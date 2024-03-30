use Gear;
insert into NhanVien(MaNV, HoTen, MatKhau, SDT, VaiTro, TrangThai, Email)
values

('NV001', N'Nguyễn Hoàng Phúc', '1111', '0938251727', 1, 0, 'asd'),
('NV002', N'Nguyễn Minh Tài', 'abc', '0938531727', 0, 0, 'kietmnvps27065@fpt.edu.vn'),
('NV003', N'Mai Nguyễn Văn Kiệt', '8888', '0938411727', 1, 0, 'kietmnvse160357@fpt.edu.vn'),
('NV004', N'Nguyễn Văn Nam', '4444', '0938220727', 1, 0, 'abc'),
('NV005', N'Nguyễn Hoàng Nam', '3333', '0938296727', 1, 0, 'cde'),
('NV006', N'Trần Ngọc Trí', '7777', '0938530727', 1, 0, 'efg'),
('NV007', N'Nguyễn Hoàng Phú', '2222', '0938841727', 0, 1, 'hik')
go
insert into KhachHang(SDT, HoTen, DiaChi, Email)
values
('0912657348', N'Trương Hoàng Minh', N'90/12/14/8 Bông Sao, P5, Q8', 'wagaa57@gmail.com'),
('0942871528', N'Hoàng Vũ An', N'53/9 Lê Văn Sỹ, P8, Q3', 'uio'),
('0916537348', N'Trương Anh Tuấn', N'90/12 Bùi Minh Trực, P6, Q8', 'qwe')
go
insert into ThuongHieu(MaTH, TenTH, Email)
values
('TH001', N'Hãng Asus', 'asusvn@gmail.com'),
('TH002', N'Hãng Gigabyte', 'gigabytevn@gmail.com'),
('TH003', N'Hãng Logitech', 'logitechvn@gmail.com'),
('TH004', N'Hãng Razer', 'razervn@gmail.com'),
('TH005', N'Hãng Intel', 'intelvn@gmail.com'),
('TH006', N'Hãng AMD', 'amdvn@gmail.com'),
('TH007', N'Hãng Dell', 'dellvn@gmail.com'),
('TH008', N'Hãng Acer', 'acervn@gmail.com'),
('TH009', N'Hãng HP', 'hpvn@gmail.com'),
('TH010', N'Hãng HyperX', 'hyperxvn@gmail.com'),
('TH011', N'Hãng Samsung', 'samsungvn@gmail.com'),
('TH012', N'Hãng Kingston', 'kingstonvn@gmail.com'),
('TH013', N'Hãng Xigmatek', 'xigmatekvn@gmail.com'),
('TH014', N'Hãng MSI', 'msivn@gmail.com'),
('TH015', N'Hãng Corsair', 'corsairvn@gmail.com'),
('TH016', N'Hãng Lenovo', 'lenovovn@gmail.com')
go
insert into LoaiSP(MaLoai, TenLoai)
values
('L001', N'Laptop'),
('L002', N'Laptop Gaming'),
('L003', N'CPU - Bộ vi xử lý'),
('L004', N'Mainboard - Bo mạch chủ'),
('L005', N'RAM - Bộ nhớ trong'),
('L006', N'Lưu trữ SSD - HDD'),
('L007', N'Case - Vỏ máy tính'),
('L008', N'PSU - Nguồn máy tính'),
('L009', N'Tản nhiệt - Fan RGB'),
('L010', N'Chuột'),
('L011', N'Pad - Lót chuột'),
('L012', N'Tai nghe'),
('L013', N'Bàn - Ghế'),
('L014', N'Màn hình'),
('L015', N'Bàn phím')
go
insert into SanPham(MaSP, TenSP, DonGia, SoLuong, MaLoai, MaTH, Mota, HinhSP)
values
--Laptop Asus
('ASUS001', N'Laptop ASUS Vivobook 15 OLED A1505ZA L1245W', '16490000', '50', 'L001', 'TH001', 
N'CPU	Intel Core i5-12500H Processor 2.5 GHz (18M Cache, up to 4.5 GHz, 4P+8E cores)
RAM	8GB (Onboard) DDR4 3200MHz (Còn 1 slot SO-DIMM, nâng cấp tối đa 16GB)
Ổ cứng	512GB SSD M.2 NVMe™ PCIe® 3.0 (1 Slot)
VGA	Intel Iris Xe Graphics (with dual channel memory)
Intel® UHD Graphics
Màn hình	15.6" FHD (1920 x 1080) OLED 16:9 aspect ratio, 0.2ms response time, 600nits peak brightness, 100% DCI-P3 color gamut, Contrast ratio: 1,000,000:1, VESA CERTIFIED Display HDR True Black 600, 1.07 billion colors, PANTONE Validated, Glossy display, 70% less harmful blue light, SGS Eye Care Display, Screen-to-body ratio: 86%
Cổng giao tiếp	
1 x USB 2.0 Type-A
1 x USB 3.2 Gen 1 Type-C
2 x USB 3.2 Gen 1 Type-A
1 x HDMI 1.4
1 x 3.5mm Combo Audio Jack
1 x DC-in
Bàn phím	LED trắng
Bảo mật	Vân tay
Audio	SonicMaster
Đọc thẻ nhớ	None
Chuẩn LAN	None
Chuẩn WIFI	Wi-Fi 6(802.11ax) (Dual band) 2*2
Bluetooth	v5.3
Webcam	720p HD camera With privacy shutter
Hệ điều hành	Windows 11 Home
Pin	50WHrs, 3S1P, 3-cell Li-ion
Trọng lượng	1.70 kg
Màu sắc	Transparent Silver
Kích thước	35.68 x 22.76 x 1.99 cm',
'AsusVivobook15.png'),

('ASUS002', N'Laptop ASUS ZenBook S13 OLED UX5304VA NQ125W', '36990000', '70', 'L001', 'TH001', 
N'CPU	Intel® Core™ i7-1355U Processor 1.7 GHz (12MB Cache, up to 5.0 GHz, 10 cores, 12 Threads)
RAM	16GB LPDDR5 on board (Không nâng cấp)
Ổ cứng	512GB M.2 NVMe™ PCIe® 4.0 Performance SSD
Màn hình	13.3" 2.8K (2880 x 1800) OLED 16:10 aspect ratio, LED Backlit, 60Hz ,0.2ms response time, 550nits, 100% DCI-P3, VESA CERTIFIED Display HDR True Black 500, 1.07 billion colors, PANTONE Validated, Glossy display, Tỉ lệ màn hình với thân máy 85％
Card màn hình	Intel® Iris Xe Graphics
Bàn phím	Backlit Chiclet Keyboard
Cổng kết nối	
1x USB 3.2 Gen 2 Type-A
2x Thunderbolt™ 4 supports display / power delivery
1x HDMI 2.1 TMDS
1x 3.5mm Combo Audio Jack
Webcam	FHD camera with IR function to support Windows Hello
Audio	Harman/Kardon (Premium)
Kết nối không dây	Wi-Fi 6E(802.11ax) (Dual band) 2*2 + Bluetooth® 5
Hệ điều hành	Windows 11 Home
Pin	4 Cells 63WHrs
Trọng lượng	1.0 kg
Màu sắc	Basalt Grey
Kích thước	29.62 x 21.63 x 1.09 ~ 1.18 cm',
'AsusZenbookS13.png'),

('ASUS003', N'Laptop Asus Zenbook Pro 14 Duo OLED UX8402VU P1028W', '65990000', '40', 'L001', 'TH001', 
N'CPU	Intel® Core™ i9-13900H Processor 2.6 GHz (24MB Cache, up to 5.4 GHz, 14 cores, 20 Threads)
RAM	32GB LPDDR5 onboard
Ổ cứng	1TB M.2 NVMe™ PCIe® 4.0 Performance SSD (1 slot)
VGA	NVIDIA® Geforce RTX™ 4050 Laptop GPU 6GB GDDR6
Màn hình	14.5" 2.8K (2880 x 1800) WQXGA+ OLED 16:10, LED Backlit, 0.2ms, 120Hz, 400nits, 100% DCI-P3, PANTONE Validated, Touch screen, With stylus support, Screen-to-body ratio: 93 ％, VESA CERTIFIED Display HDR True Black 500
Màn hình nhỏ	ScreenPad™ Plus (12.7" 2880 x 864 IPS-level Panel Support Stylus)
Cổng giao tiếp	
1x USB 3.2 Gen 2 Type-A
2x Thunderbolt™ 4 supports display / power delivery
1x HDMI 2.1 FRL
1x 3.5mm Combo Audio Jack
1x DC-in
Micro SD Express 7.1 card reader
Bàn phím	Có LED
Audio	Harman/Kardon (Premium)
Đọc thẻ nhớ	SD Express 7.0 card reader
Chuẩn LAN	None
Chuẩn WIFI	Wi-Fi 6E(802.11ax) (2x2)
Bluetooth	v5.2
Webcam	720p FHD IR webcam with Windows Hello support
Hệ điều hành	Windows 11 Home
Pin	4 Cells 76WHrs
Trọng lượng	1.75 kg
Màu sắc	Tech Black Aluminum
Kích thước	32.35 x 22.47 x 1.79 ~ 1.96 (cm)',
'AsusZenbookPro14Duo.png'),

--Laptop MSI
('MSI001', N'Laptop MSI Modern 14 C13M 607VN', '19490000', '50', 'L001', 'TH014', 
N'CPU	Intel Core i7-1355U 1.7GHz up to 5.0GHz 12MB
RAM	16GB Onboard DDR4 3200MHz
Ổ cứng	512GB NVMe PCIe Gen 3x4 SSD (1 Slot)
Card đồ họa	Intel Iris Xe Graphics
Màn hình	14" FHD (1920 x 1080) IPS-Level, 60Hz, 45% NTSC, Thin Bezel, 65% sRGB
Cổng giao tiếp
1x Type-C (USB 3.2 Gen 2 / DP) with PD charging
1x Type-A USB 3.2 Gen 2
2x Type-A USB 2.0
1x HDMI™ (4K @ 30Hz)
1x Mic-in/Headphone-out Combo Jack
Micro SD Card Reader
Bàn phím	Backlight Keyboard (Single-Color, White)
Chuẩn LAN	Không có
Chuẩn WIFI	Intel Wi-Fi 6 AX201 (2x2)
Bluetooth	Bluetooth 5.2
Webcam	HD 720p 30fps
Hệ điều hành	Windows 11 Home
Pin	3 cell, 39.3Whr
Trọng lượng	1.4 kg
Màu sắc	Classic Black
Kích thước	319.9 x 223 x 19.35 (mm)',
'MSIModern14.png'),

('MSI002', N'Laptop MSI Prestige 13 Evo A13M 081VN', '29990000', '60', 'L001', 'TH014', 
N'CPU	Intel Core i7-1360P 2.2GHz up to 5.0GHz 18MB
RAM	16GB (8x2) LPDDR5 4800MHz Onboard
Ổ cứng	1TB PCIe (1 slot, support M.2 2280 PCIe 4.0x4) 
Card đồ họa	Intel® Iris® Xe graphics
Màn hình	13.3" WUXGA (1920x1200) IPS
Cổng giao tiếp	
2x Type-C (USB / DP / Thunderbolt™ 4) with PD charging
1x Type-A USB 3.2 Gen 1
1x HDMI™ 2.1 (4K @ 60Hz)
1x Mic-in/Headphone-out Combo Jack
Fingerprint
Micro SD Card Reader
Audio	2x 2W Speaker
Bàn phím	Single Backlit Keyboard (White)
Chuẩn WIFI	Intel® Killer™ AX Wi-Fi 6E
Bluetooth	v5.3
Webcam	IR FHD type (30fps@1080p)
Hệ điều hành	Windows 11 Home
Pin	4 Cell 75WHr
Trọng lượng	0.99 kg
Màu sắc	Urban Silver
Kích thước	299 x 210 x 16.9 mm',
'MSIPrestige13.png'),

('MSI003', N'Laptop MSI Creator Z17 HX Studio A13VGT 068VN', '72490000', '30', 'L001', 'TH014', 
N'CPU	Intel® Core™ i7-13700HX (Up to 5.00 GHz, 24 MB), 16 Cores 24 Threads, 30 MB Intel® Smart Cache
RAM	32GB (2x16GB) DDR5 5600MHz (2x SO-DIMM socket, up to 64GB SDRAM)
Ổ cứng	2TB SSD NVMe PCIe Gen 4x4 
Card đồ họa	NVIDIA GeForce RTX 4070 Laptop GPU 8GB GDDR6 Up to 1980MHz Boost Clock 90W Maximum Graphics Power with Dynamic Boost.
Màn hình	17 Inch 2K QHD+ (2560x1600),IPS,165Hz, 100% DCI-P3, TouchScreen, Support MSI Pen
Cổng giao tiếp	
1x USB Type-C / DP / Thunderbolt 4 with PD charging
1x USB Type-C / DP / Thunderbolt 4
1x USB 3.2 Gen2 Type-A
1x SD Express Card Reader
1x HDMI™ 2.1
1x Mic-in/Headphone-out Combo Jack
Audio	Dynaudio
Bàn phím	RGB Per-Key, touch pad hỗ trợ đa điểm
Chuẩn WIFI	Killer AX Wi-Fi 6E
Bluetooth	v5.3
Webcam	IR HD type (30fps@720p)
Hệ điều hành	Windows 11 Home
Pin	4 Cell 90WHr
Trọng lượng	2.49 kg
Màu sắc	Lunar Gray
Kích thước	382 x 260 x 19 mm',
'MSICreatorZ17.png'),

--Laptop Gaming Lenovo
('LENOVO001', N'Laptop gaming Lenovo Legion 5 Pro 16IAH7H 82RF0044VN', '53990000', '70', 'L002', 'TH016', 
N'CPU	Intel Core i7-12700H, 14C (6P + 8E) / 20T, P-core 2.3 / 4.7GHz, E-core 1.7 / 3.5GHz, 24MB
RAM	16GB (2x8GB) DDR5 4800MHz (2x SO-DIMM socket, up to 32GB SDRAM)
Ổ cứng	512GB SSD M.2 2280 PCIe 4.0x4 NVMe (2 slots)
Card đồ họa	NVIDIA GeForce RTX 3070 Ti 8GB GDDR6, Boost Clock 1485MHz, TGP 150W
Màn hình	16" WQXGA (2560x1600) IPS 500nits Anti-glare, 165Hz, 100% sRGB, Dolby Vision, HDR 400, G-Sync, DC dimmer, Low Blue Light, High Gaming Performance
Cổng giao tiếp	
2x USB 3.2 Gen 1
1x USB 3.2 Gen 1 (Always On)
1x USB-C 3.2 Gen 2 (support data transfer and DisplayPort 1.4)
1x USB-C 3.2 Gen 2 (support data transfer, Power Delivery 135W and DisplayPort 1.4)
1x Thunderbolt 4 / USB4 40Gbps (support data transfer and DisplayPort 1.4)
1x HDMI, up to 8K/60Hz
1x Ethernet (RJ-45)
1x Headphone / microphone combo jack (3.5mm)
1x Power connector
Bàn phím	LED RGB 4-Zone
Audio	Stereo speakers, 2W x2, Nahimic Audio
Đọc thẻ nhớ	None
Chuẩn LAN	100/1000M
Chuẩn WIFI	Wi-Fi 6 11ax, 2x2
Bluetooth	v5.1
Webcam	FHD 1080p with E-camera Shutter
Hệ điều hành	Windows 11 Home
Pin	4Cell, 80WHrs
Trọng lượng	2.49 kg
Màu sắc	Storm Grey
Kích thước	359.9 x 264.4 x 19.9 mm (14.17 x 10.41 x 0.78 inches)',
'LenovoLegion5Pro.png'),

('LENOVO002', N'Laptop gaming Lenovo Ideapad gaming 3 15ARH7 82SB00JUVN', '24990000', '90', 'L002', 'TH016', 
N'CPU	AMD Ryzen™ 5 7535HS ( 6 Cores   12 Threads, 3.3 / 4.55GHz, 3MB L2 / 16MB L3)
Ram	8GB (8GBx1) DDR5 4800MHz (2x SO-DIMM socket, up to 16GB SDRAM)
Ổ cứng	512GB SSD M.2 2242 PCIe 4.0x4 NVMe (Còn trống 1 khe SSD M.2 2280 up to 1TB)
Card đồ họa	NVIDIA® GeForce RTX™ 4050 6GB GDDR6, Boost Clock 2370MHz, TGP 85W
Màn hình	15.6" FHD (1920x1080) IPS 250nits Anti-glare, 45% NTSC, 120Hz, FreeSync™
Cổng kết nối	
2x USB 3.2 Gen 1
1x USB-C® 3.2 Gen 2 (support data transfer, Power Delivery 3.0 and DisplayPort™ 1.4)
1x HDMI® 2.0
1x Ethernet (RJ-45)
1x Headphone / microphone combo jack (3.5mm)
1x Power connector
Bàn phím	White Backlit
Audio	Stereo speakers, 2W x2, Nahimic Audio
Chuẩn Lan	100/1000M (RJ-45)
Wifi + Bluetooth	Wi-Fi 6 11ax, 2x2 + BT v5.1
Webcam	HD 720p with Privacy Shutter
Hệ điều hành    	Windows 11 Home
Pin	Integrated 60Wh
Trọng lượng	2.32 kg
Màu sắc	Onyx Grey
Kích thước    	359.6 x 266.4 x 21.8 mm',
'LenovoIdeaPadGaming3.png'),

('LENOVO003', N'Laptop gaming Lenovo LOQ 15IRH8 82XV000PVN', '29990000', '80', 'L002', 'TH016', 
N'CPU	Intel® Core™ i5-13420H, 8 Cores (4P + 4E) / 12 Threads, P-core up to 4.6GHz, E-core up to 3.4GHz, 12MB
RAM	1 x 8GB DDR5 5200MHz (2x SO-DIMM socket, up to 16GB SDRAM)
Ổ cứng	512GB SSD M.2 2242 PCIe 4.0x4 NVMe (2 Slots: M2 2242 PCIe 4.0 x4 Slot, M.2 2280 PCIe 4.0 x4 Slot)
Card đồ họa	NVIDIA® GeForce RTX™ 4050 6GB GDDR6, Boost Clock 2370MHz, TGP 95W
Màn hình	15.6" FHD (1920x1080) IPS 350nits Anti-glare, 45% NTSC, 144Hz, G-SYNC
Cổng kết nối	
1x USB 3.2 Gen 1
2x USB 3.2 Gen 2
1x USB-C® 3.2 Gen 2 (support data transfer, Power Delivery 140W and DisplayPort™ 1.4)
1x HDMI®, up to 8K/60Hz
1x Ethernet (RJ-45)
1x Headphone / microphone combo jack (3.5mm)
1x Power connector
Bàn phím	4-Zone RGB Backlit, English
Audio	High Definition (HD) Audio, Realtek® ALC3287 codec
Chuẩn Lan	100/1000M
Wifi + Bluetooth	Wi-Fi 6 11ax, 2x2 + BT5.1
Webcam	FHD 1080p with E-shutter
Hệ điều hành    	Windows 11 Home
Pin	Integrated 60Wh
Trọng lượng	2.4 kg
Màu sắc	Storm Grey
Kích thước	359.6 x 264.8 x 22.1-25.2 mm',
'LenovoLOQ.png'),

--CPU Intel
('INTEL001', N'Bộ vi xử lý Intel Core i3 12100F', '2290000', '100', 'L003', 'TH005', 
N'Model	Bộ vi xử lý Intel Core i3 gen 12th
Số hiệu xử lí	i3-12100F
Số nhân	4
Số luồng	8
Tần số turbo tối đa	4.30 GHz
Tần số turbo tối đa của lõi hiệu suất	4.30 GHz
Tần số cơ bản của lõi hiệu suất	3.30 GHz
Bộ nhớ đệm	12 MB Intel Smart Cache
Total L2 cache	5 MB
Công suất cơ bản	58 W
Công suất tối đa	89 W
Các loại bộ nhớ	Up to DDR5 4800 MT/s
Up to DDR4 3200 MT/s
Dung lượng tối đa	128 GB
Bộ nhớ đa kênh	2
Băng thông tối đa	76.8 GB/s
Phiên bản PCI Express	5.0 và 4.0
Cấu hình PCI Express	Up to 1x16+4, 2x8+4
Số cổng PCI Express tối đa	20
Hỗ trợ socket	FCLGA1700
Cấu hình CPU tối đa	1
Kích thước	45.0 mm x 37.5 mm',
'i3gen12th.png')




go
insert into KhuyenMai(MaKM, TenKM, NgayBatDau, NgayKetThuc)
values
('KM001', N'Khuyến mãi 30/4 - 01/05', '2022-04-25', '2022-05-05'),
('KM002', N'Khuyến mãi 02/09', '2022-08-25', '2022-09-05'),
('KM003', N'Khuyến mãi Sinh Nhật GearShop', '2022-06-21', '2022-06-30'),
('KM004', N'Khuyến mãi Tết Nguyên đán', '2023-01-15', '2022-02-05')

go
insert into ChiTietKhuyenMai(MaKM, MaSP, TiLeKM)
values
('KM001', 'LENOVO002', '29'),
('KM002', 'INTEL001', '36'),
('KM003', 'MSI003', '45'),
('KM004', 'ASUS001', '15')

go
insert into HoaDon(MaHD, NgayLap, MaNV, sdtKH)
values
('HD001', '2023-07-18', 'NV001', '0912657348'),
('HD002', '2023-07-18', 'NV003', '0942871528'),
('HD003', '2023-07-18', 'NV005', '0916537348')

go
insert into HoaDonChiTiet(SoLuong, MaSP, MaHD)
values
('01', 'LENOVO002','HD005'),
('01', 'LENOVO001','HD005'),
('01', 'ASUS002', 'HD006'),
('03', 'MSI002', 'HD007')

go
insert into NhaCungCap(MaNCC, TenNCC, SDT, DiaChi)
values
('NCC001', N'Asus', '0286392417','Tòa nhà Viettel Complex, Tầng 1 Hẻm 285 Cách Mạng Tháng Tám, P.12, Q.10, HCM'),
('NCC002', N'Gigabyte', '0288522417', '175 Nguyễn Thị Minh Khai, P.Phạm Ngũ Lão, Quận 1, HCM'),
('NCC003', N'Logitech', '0248596583','TAIYO Office,Tầng 5, 27 Trần Duy Hưng, P.Trung Hòa, Q.Cầu Giấy, Hà Nội.'),
('NCC004', N'Razer', '0288852983','194/3 Nguyễn Trọng Tuyển, P.8, Q.Phú Nhuận, HCM'),
('NCC005', N'Intel', '0285252983','Hi-Tech Park, Lô I2, Đ. D1, P.Tân Phú, Q.9, HCM'),
('NCC006', N'AMD', '0285854983','Tầng 9. Paxsky, 13-15-17 Trương Định, P.6, Q.3, HCM'),
('NCC007', N'Dell', '0289573143','23 Nguyễn Thị Huỳnh, P.8, Q.Phú Nhuận, HCM'),
('NCC008', N'Acer', '0288571343','704-05 Sai Gon Trade Center building, 37 Tôn Đức Thắng, P.Bến Nghé, Q.1, HCM'),
('NCC009', N'HP', '0289758612','29 Đ. Lê Duẩn, P.Bến Nghé, Q.1, HCM'),
('NCC010', N'Kingston HyperX', '0287651942','3/R3A Ba Vì, Cư xá Bắc Hải, P.15, Q.10, HCM'),
('NCC011', N'Samsung', '0288957742','Tòa nhà tài chính Bitexco, 2 Hải Triều, P.Bến Nghé, Q.1, HCM'),
('NCC012', N'Xigmatek', '0285590842','Số 104 Lô P29, Đường 14, Khu đô thị mới Him Lam, Q.7, HCM'),
('NCC013', N'MSI', '0287415693','264 Đ.Nguyễn Thị Minh Khai, P.6, Q.3, HCM'),
('NCC014', N'Corsair', '028.73001088','658/21 Cách Mạng Tháng Tám, P.11, Q.3, HCM'),
('NCC015', N'Lenovo', '02838243504','Empress Tower, 138-142 Hai Bà Trưng, P.Đa Kao, Q.1, HCM,')

go
insert into PhieuNhap(MaPhieu, NgayNhap, MaNCC, MaNV)
values
('PN001', '2022-01-02', 'NCC001', 'NV001'),
('PN002', '2022-01-03', 'NCC015', 'NV003'),
('PN003', '2022-01-02', 'NCC013', 'NV004'),
('PN004', '2022-01-02', 'NCC005', 'NV005')

go
insert into ChiTietPhieuNhap(MaPhieu, MaSP, SoLuong)
values
('PN001', 'ASUS001', '50'),
('PN001', 'ASUS002', '70'),
('PN001', 'ASUS003', '40'),
('PN002', 'LENOVO001', '70'),
('PN002', 'LENOVO002', '90'),
('PN002', 'LENOVO003', '80'),
('PN003', 'MSI001', '50'),
('PN003', 'MSI001', '60'),
('PN004', 'INTEL001', '100')