USE master;DROP DATABASE Gear;CREATE DATABASE [Gear];
-- Kiểm tra tồn tại và xóa đăng nhập nếu đã tồn tại
IF EXISTS (SELECT * FROM sys.server_principals WHERE name = 'NewLoginName')
BEGIN
    DROP LOGIN NewLoginName;
END
-- Kiểm tra tồn tại và xóa người dùng nếu đã tồn tại
IF EXISTS (SELECT * FROM sys.database_principals WHERE name = 'NewUserName')
BEGIN
    DROP USER NewUserName;
END
-- Tạo đăng nhập mới và người dùng mới
CREATE LOGIN NewLoginName WITH PASSWORD = 'YourPassword';
CREATE USER NewUserName FOR LOGIN NewLoginName;
-- Thêm người dùng vào server role cụ thể
ALTER SERVER ROLE [sysadmin] ADD MEMBER [NewLoginName];
USE [Gear]
GO
create table NhanVien(
	MaNV varchar(10) primary key,
	HoTen nvarchar(50) not null,
	MatKhau varchar(max) not null,
	SDT varchar(15) not null,
	VaiTro bit not null,
	TrangThai nvarchar(max) not null,
	Email varchar(50) not null unique
);
go
create table KhachHang(
	SDT varchar(15) primary key,
	HoTen nvarchar(50) not null,
	DiaChi nvarchar(max) not null,
	Email varchar(50) not null unique
);
go
create table ThuongHieu(
	MaTH varchar(10) primary key,
	TenTH nvarchar(50) not null,
	Email varchar(50) not null unique,
);
go
create table LoaiSP(
	MaLoai varchar(10) primary key,
	TenLoai nvarchar(50) not null,
);
go
create table SanPham(
	MaSP varchar(10) primary key,
	TenSP nvarchar(max) not null,
	DonGia int not null,
	SoLuong int not null,
	MaLoai varchar(10),
	foreign key (MaLoai) references LoaiSP(MaLoai) on update cascade,
	MaTH varchar(10),
	foreign key (MaTH) references ThuongHieu(MaTH) on update cascade,
	Mota nvarchar(max) not null,
	HinhSP varchar(max) not null
);
go
create table KhuyenMai(
	MaKM varchar(10) primary key,
	TenKM nvarchar(50) not null,
	NgayBatDau date not null,
	NgayKetThuc date not null,
);
go
create table ChiTietKhuyenMai(
	MaKM varchar(10),
	foreign key (MaKM) references KhuyenMai(MaKM) on update cascade,
	MaSP varchar(10),
	foreign key (MaSP) references SanPham(MaSP) on update cascade,
	TiLeKM float not null
);
go
create table HoaDon(
	MaHD varchar(10) primary key,
	NgayLap date default getdate(),
	MaNV varchar(10),
	sdtKH varchar(15),
	TongTien int,
	foreign key (sdtKH) references KhachHang(SDT) on update cascade,
	foreign key (MaNV) references NhanVien(MaNV) on update cascade
);
go
create table HoaDonChiTiet(
	SoLuong int not null,
	MaSP varchar(10),
	MaHD varchar(10),
	foreign key (MaSP) references SanPham(MaSP) on update cascade,
	foreign key (MaHD) references HoaDon(MaHD) on update cascade
);
go
create table NhaCungCap(
	MaNCC varchar(10) primary key,
	TenNCC nvarchar(50) not null,
	SDT varchar(15) not null,
	DiaChi nvarchar(max) not null
);
create table PhieuNhap(
	MaPhieu varchar(10) primary key,
	NgayNhap date not null,
	MaNCC varchar(10),
	MaNV varchar(10),
	foreign key (MaNCC) references NhaCungCap(MaNCC) on update cascade,
	foreign key (MaNV) references NhanVien(MaNV) on update cascade
);
go
create table ChiTietPhieuNhap(
	MaPhieu varchar(10),
	MaSP varchar(10),
	SoLuong int not null,
	foreign key (MaPhieu) references PhieuNhap(MaPhieu) on update cascade,
	foreign key (MaSP) references SanPham(MaSP) on update cascade
);
GO
INSERT INTO NhanVien VALUES (N'admin', N'admin', N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', N'0333491639', 0, N'admin', N'ilovevy19@gmail.com')