USE master
GO

DROP DATABASE EShop
GO

CREATE DATABASE EShop;
GO

USE EShop;
GO

-- --------------------------------------------------------

--
-- Table structure for table `Account`
--
CREATE TABLE Account(
    AccountId INT IDENTITY(1, 1) PRIMARY KEY,
    Username NVARCHAR(50) UNIQUE NOT NULL,
    [Password] NVARCHAR(50) NOT NULL,
    Fullname NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    Photo NVARCHAR(255) NOT NULL,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    IsActive BIT NOT NULL DEFAULT 1,
    IsAdmin BIT NOT NULL DEFAULT 0,
);
GO

--
-- Table structure for table `Category`
--
CREATE TABLE Category(
    CategoryId INT IDENTITY(1, 1) PRIMARY KEY,
    CategoryName NVARCHAR(50) NOT NULL,
);
GO

--
-- Table structure for table `SubCategory`
--
CREATE TABLE SubCategory(
    SubCategoryId INT IDENTITY(1, 1) PRIMARY KEY,
    SubCategoryName NVARCHAR(50) NOT NULL,
    CategoryId INT NOT NULL,
    FOREIGN KEY (CategoryId) REFERENCES Category(CategoryId)
);
GO

--
-- Table structure for table `Product`
--
CREATE TABLE Product(
    ProductId INT IDENTITY(1, 1) PRIMARY KEY,
    ProductName NVARCHAR(50) NOT NULL,
    ProductImage VARCHAR(150) NOT NULL,
    [Description] VARCHAR(MAX) NOT NULL,
    Quantity INT NOT NULL,
    UnitPrice FLOAT NOT NULL,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    Available INT NOT NULL DEFAULT 0, -- 0: Còn hàng, 1: Hết hàng, 2: Ngừng kinh doanh
    CategoryId INT NOT NULL,
    SubCategoryId INT NOT NULL,
    FOREIGN KEY (CategoryId) REFERENCES Category(CategoryId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (SubCategoryId) REFERENCES SubCategory(SubCategoryId) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

--
-- Table structure for table `Discount`
--
CREATE TABLE Discount(
    DiscountId INT IDENTITY(1, 1) PRIMARY KEY,
    DiscountName NVARCHAR(50) NOT NULL,
    DiscountPercent FLOAT NOT NULL,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME NOT NULL,
    IsActive BIT NOT NULL DEFAULT 1,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE()
);

--
-- Table structure for table `ShoppingCart`
--
CREATE TABLE ShoppingCart(
    CartId INT IDENTITY(1, 1) PRIMARY KEY,
    AccountId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NOT NULL,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(), 
    FOREIGN KEY (AccountId) REFERENCES Account(AccountId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

--
-- Table structure for table `Review`
--
CREATE TABLE Review(
    ReviewId INT IDENTITY(1, 1) PRIMARY KEY,
    ReviewTitle NVARCHAR(50) NOT NULL,
    ReviewContent NVARCHAR(MAX) NOT NULL,
    Rating INT NOT NULL,
    AccountId INT NOT NULL,
    ProductId INT NOT NULL,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (AccountId) REFERENCES Account(AccountId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

--
-- Table structure for table `Order`
--
CREATE TABLE [Order](
    OrderId BIGINT IDENTITY(1, 1) PRIMARY KEY,
    AccountId INT NOT NULL,
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    ShipDate DATETIME NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (AccountId) REFERENCES Account(AccountId) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

--
-- Table structure for table `OrderDetail`
--
CREATE TABLE OrderDetail(
    OrderDetailId BIGINT IDENTITY(1, 1) PRIMARY KEY,
    OrderId BIGINT NOT NULL,
    ProductId INT NOT NULL,
    UnitPrice FLOAT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrderId) REFERENCES [Order](OrderId) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

-- --------------------------------------------------------
--
--  Data for table `Category`
--
SET IDENTITY_INSERT Category ON
INSERT INTO Category (CategoryId, CategoryName) VALUES
(0, N'Điện thoại'),
(1, N'Máy tính xách tay'),
(2, N'Máy tính bảng'),
(3, N'Phụ kiện'),
(4, N'Đồng hồ')
GO
SET IDENTITY_INSERT Category OFF

--
--  Data for table `Product`
--
SET IDENTITY_INSERT Product ON
INSERT INTO Product(ProductId, ProductName, ProductImage, UnitPrice, CreatedDate, Available, CategoryId) VALUES
(0, N'Samsung Galaxy S20 FE', N'https://cdn.tgdd.vn/Products/Images/42/224859/samsung-galaxy-s20-fan-edition-090320-040338-600x600.jpg', 13490000, GETDATE(), 1, 0),
(1, N'Samsung Galaxy Z Fold3 5G 256GB', N'https://cdn.tgdd.vn/Products/Images/42/226935/samsung-galaxy-z-fold-3-silver-1-600x600.jpg', 41990000, GETDATE(), 1, 0),
(2, N'Samsung Galaxy A52 128GB', N'https://cdn.tgdd.vn/Products/Images/42/228967/samsung-galaxy-a52-8gb-256gb-thumb-violet-600x600-600x600.jpg', 8290000, GETDATE(), 1, 0),
(3, N'iPhone 11 64GB', N'https://cdn.tgdd.vn/Products/Images/42/153856/iphone-xi-do-600x600.jpg', 15990000, GETDATE(), 1, 2),
(4, N'iPhone 13 Pro Max 256GB', N'https://cdn.tgdd.vn/Products/Images/42/250261/iphone-13-pro-max-gold-1-600x600.jpg', 34990000, GETDATE(), 1, 2),
(5, N'iPhone 12 Pro Max 512GB', N'https://cdn.tgdd.vn/Products/Images/42/228744/iphone-12-pro-max-vang-new-600x600-600x600.jpg', 36990000, GETDATE(), 1, 2),
(6, N'OPPO Reno6 Z 5G', N'https://cdn.tgdd.vn/Products/Images/42/224859/samsung-galaxy-s20-fan-edition-090320-040338-600x600.jpg', 9490000, GETDATE(), 1, 1),
(7, N'OPPO A95', N'https://cdn.tgdd.vn/Products/Images/42/251703/oppo-a95-4g-bac-2-600x600.jpg', 6990000, GETDATE(), 1, 1),
(8, N'OPPO Find X3 Pro 5G', N'https://cdn.tgdd.vn/Products/Images/42/232190/oppo-find-x3-pro-black-001-1-600x600.jpg', 19490000, GETDATE(), 1, 1)
GO
SET IDENTITY_INSERT Product OFF

--
--  Data for table `Account`
--
SET IDENTITY_INSERT Account ON
INSERT INTO Account (AccountId, Username, [Password], Fullname, Email, Photo, IsActive, IsAdmin) VALUES
(0, N'TeoNV', '123456', N'Nguyễn Văn Tèo', N'teonv@gmail.com', N'https://randomuser.me/api/portraits/men/26.jpg', 1, 1),
(1, N'VietTN', '123456', N'Trần Nhật Việt', N'vietnt@gmail.com', N'https://randomuser.me/api/portraits/men/10.jpg', 1, 0),
(2, N'MaiNT', '123456', N'Nguyễn Thị Mai', N'maint@gmail.com', N'https://randomuser.me/api/portraits/women/60.jpg', 1, 1),
(3, N'AnhPTQ', '123456', N'Phan Thị Quỳnh Anh', N'anhptq@gmail.com', N'https://randomuser.me/api/portraits/women/76.jpg', 1, 0)
GO
SET IDENTITY_INSERT Account OFF

--
--  Data for table `Order`
--
SET IDENTITY_INSERT [Order] ON
INSERT INTO [Order] (OrderId, AccountId, CreatedDate) VALUES
(0, 0, GETDATE()),
(1, 1, GETDATE()),
(2, 2, GETDATE()),
(3, 3, GETDATE())
GO
SET IDENTITY_INSERT [Order] OFF

