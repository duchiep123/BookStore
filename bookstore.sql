USE [master]
GO
/****** Object:  Database [BookStore ]    Script Date: 11/28/2019 11:53:10 PM ******/
CREATE DATABASE [BookStore ]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BookStore', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\BookStore .mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'BookStore _log', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\BookStore _log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [BookStore ] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookStore ].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookStore ] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookStore ] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookStore ] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookStore ] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookStore ] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookStore ] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BookStore ] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookStore ] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookStore ] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookStore ] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookStore ] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookStore ] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookStore ] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookStore ] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookStore ] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BookStore ] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookStore ] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookStore ] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookStore ] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookStore ] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookStore ] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookStore ] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookStore ] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [BookStore ] SET  MULTI_USER 
GO
ALTER DATABASE [BookStore ] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookStore ] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookStore ] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookStore ] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [BookStore ] SET DELAYED_DURABILITY = DISABLED 
GO
USE [BookStore ]
GO
/****** Object:  Table [dbo].[BookTable]    Script Date: 11/28/2019 11:53:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BookTable](
	[BookID] [varchar](50) NOT NULL,
	[BookName] [varchar](50) NOT NULL,
	[Description] [varchar](300) NOT NULL,
	[Category] [int] NOT NULL,
	[Price] [float] NOT NULL,
	[Status] [int] NOT NULL,
	[Author] [varchar](50) NOT NULL,
	[Image] [varchar](max) NOT NULL,
	[Quantity] [int] NOT NULL,
	[ImportDate] [date] NOT NULL,
 CONSTRAINT [PK_ProductTable] PRIMARY KEY CLUSTERED 
(
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CategoryTable]    Script Date: 11/28/2019 11:53:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CategoryTable](
	[Category] [int] NOT NULL,
	[Description] [varchar](50) NOT NULL,
 CONSTRAINT [PK_CategoryTable] PRIMARY KEY CLUSTERED 
(
	[Category] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DiscountTable]    Script Date: 11/28/2019 11:53:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DiscountTable](
	[DiscountCode] [varchar](50) NOT NULL,
	[Value] [float] NOT NULL,
	[Status] [bit] NOT NULL,
	[UserID] [varchar](50) NULL,
 CONSTRAINT [PK_DiscountTable] PRIMARY KEY CLUSTERED 
(
	[DiscountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetailTable]    Script Date: 11/28/2019 11:53:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderDetailTable](
	[OrderID] [int] NOT NULL,
	[BookID] [varchar](50) NOT NULL,
	[Quantity] [int] NOT NULL,
	[UnitPrice] [float] NOT NULL,
	[BookName] [varchar](50) NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderTable]    Script Date: 11/28/2019 11:53:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderTable](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[TotalPrice] [float] NOT NULL,
	[CustomerID] [varchar](50) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
 CONSTRAINT [PK_OrderTable] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RoleTable]    Script Date: 11/28/2019 11:53:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RoleTable](
	[Role] [int] NOT NULL,
	[Description] [varchar](50) NOT NULL,
 CONSTRAINT [PK_RoleTable] PRIMARY KEY CLUSTERED 
(
	[Role] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[StatusTable]    Script Date: 11/28/2019 11:53:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[StatusTable](
	[Status] [int] NOT NULL,
	[Description] [varchar](50) NOT NULL,
 CONSTRAINT [PK_StatusTable] PRIMARY KEY CLUSTERED 
(
	[Status] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UserTable]    Script Date: 11/28/2019 11:53:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UserTable](
	[UserID] [varchar](50) NOT NULL,
	[Fullname] [varchar](50) NOT NULL,
	[Tel] [varchar](10) NOT NULL,
	[Address] [varchar](200) NOT NULL,
	[Password] [varchar](100) NULL,
	[Role] [int] NULL,
 CONSTRAINT [PK_UserTable] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[BookTable] ([BookID], [BookName], [Description], [Category], [Price], [Status], [Author], [Image], [Quantity], [ImportDate]) VALUES (N'B000001', N'Workplace Harassment Law', N'Good good', 4, 495, 1, N'Gillbert F. Casellas', N'data:image/jpeg;base64,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', 50, CAST(N'2019-11-24' AS Date))
INSERT [dbo].[BookTable] ([BookID], [BookName], [Description], [Category], [Price], [Status], [Author], [Image], [Quantity], [ImportDate]) VALUES (N'B000002', N'Falling for you', N'Good Good ', 3, 150, 1, N'Charlotte Blake', N'https://i.pinimg.com/474x/24/c0/97/24c0976a702e8a86c1feec28a6ad8a59.jpg', 14, CAST(N'2019-11-24' AS Date))
INSERT [dbo].[BookTable] ([BookID], [BookName], [Description], [Category], [Price], [Status], [Author], [Image], [Quantity], [ImportDate]) VALUES (N'B000003', N'Experiments from the Wilder Side of Science', N'Good Good', 2, 74.95, 1, N'	
Reto U. Schneider ', N'https://images-na.ssl-images-amazon.com/images/I/51YRa3ArrRL._SX325_BO1,204,203,200_.jpg', 50, CAST(N'2019-11-24' AS Date))
INSERT [dbo].[BookTable] ([BookID], [BookName], [Description], [Category], [Price], [Status], [Author], [Image], [Quantity], [ImportDate]) VALUES (N'B000004', N'How Big Are Your Worries Little Bear?', N'Good Good', 3, 99.029998779296875, 1, N'Jayneen Sanders', N'http://static1.squarespace.com/static/50769910e4b07864e5d07147/507752e3e4b08600fd85edc9/5a271fefec212d9abd828a69/1558933381584/1-HowBigAreYourWorries.png?format=1500w', 20, CAST(N'2019-11-24' AS Date))
INSERT [dbo].[BookTable] ([BookID], [BookName], [Description], [Category], [Price], [Status], [Author], [Image], [Quantity], [ImportDate]) VALUES (N'B000005', N'Human Anatomy', N'Good Good', 5, 49, 2, N'Alice M Roberts', N'https://d1vzko4h6qahek.cloudfront.net/images/2/books/medium/HANA.jpg', 52, CAST(N'2019-11-24' AS Date))
INSERT [dbo].[BookTable] ([BookID], [BookName], [Description], [Category], [Price], [Status], [Author], [Image], [Quantity], [ImportDate]) VALUES (N'B454545', N'Kimochi', N'Good Good                
            ', 1, 25.600000381469727, 1, N'HiepNguyen', N'data:image/jpeg;base64,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', 6, CAST(N'2019-11-27' AS Date))
INSERT [dbo].[CategoryTable] ([Category], [Description]) VALUES (1, N'Romance')
INSERT [dbo].[CategoryTable] ([Category], [Description]) VALUES (2, N'Science')
INSERT [dbo].[CategoryTable] ([Category], [Description]) VALUES (3, N'Children')
INSERT [dbo].[CategoryTable] ([Category], [Description]) VALUES (4, N'Law')
INSERT [dbo].[CategoryTable] ([Category], [Description]) VALUES (5, N'Medical')
INSERT [dbo].[DiscountTable] ([DiscountCode], [Value], [Status], [UserID]) VALUES (N'ABC', 0.01, 1, NULL)
INSERT [dbo].[DiscountTable] ([DiscountCode], [Value], [Status], [UserID]) VALUES (N'HIEPDEPTRAI', 0.05, 0, N'hong')
INSERT [dbo].[OrderDetailTable] ([OrderID], [BookID], [Quantity], [UnitPrice], [BookName]) VALUES (20, N'B000004', 1, 99.029998779296875, N'How Big Are Your Worries Little Bear?')
SET IDENTITY_INSERT [dbo].[OrderTable] ON 

INSERT [dbo].[OrderTable] ([OrderID], [TotalPrice], [CustomerID], [CreateDate]) VALUES (20, 94.078498840332031, N'hong', CAST(N'2019-11-28 23:48:03.897' AS DateTime))
SET IDENTITY_INSERT [dbo].[OrderTable] OFF
INSERT [dbo].[RoleTable] ([Role], [Description]) VALUES (1, N'admin')
INSERT [dbo].[RoleTable] ([Role], [Description]) VALUES (2, N'customer')
INSERT [dbo].[StatusTable] ([Status], [Description]) VALUES (1, N'active')
INSERT [dbo].[StatusTable] ([Status], [Description]) VALUES (2, N'inactive')
INSERT [dbo].[UserTable] ([UserID], [Fullname], [Tel], [Address], [Password], [Role]) VALUES (N'duchiep', N'Nguyen Duc Hiep', N'0338546575', N'Dong Nai', N'123', 1)
INSERT [dbo].[UserTable] ([UserID], [Fullname], [Tel], [Address], [Password], [Role]) VALUES (N'hiepnguyen', N'Nguyen Hiep', N'1234567890', N'Dong Nai', N'123', 2)
INSERT [dbo].[UserTable] ([UserID], [Fullname], [Tel], [Address], [Password], [Role]) VALUES (N'hong', N'Doan Nguyen Nam Hong', N'0123456789', N'Quan Cam', N'123', 2)
ALTER TABLE [dbo].[BookTable]  WITH CHECK ADD  CONSTRAINT [FK_BookTable_CategoryTable] FOREIGN KEY([Category])
REFERENCES [dbo].[CategoryTable] ([Category])
GO
ALTER TABLE [dbo].[BookTable] CHECK CONSTRAINT [FK_BookTable_CategoryTable]
GO
ALTER TABLE [dbo].[BookTable]  WITH CHECK ADD  CONSTRAINT [FK_BookTable_StatusTable] FOREIGN KEY([Status])
REFERENCES [dbo].[StatusTable] ([Status])
GO
ALTER TABLE [dbo].[BookTable] CHECK CONSTRAINT [FK_BookTable_StatusTable]
GO
ALTER TABLE [dbo].[DiscountTable]  WITH CHECK ADD  CONSTRAINT [FK_DiscountTable_UserTable] FOREIGN KEY([UserID])
REFERENCES [dbo].[UserTable] ([UserID])
GO
ALTER TABLE [dbo].[DiscountTable] CHECK CONSTRAINT [FK_DiscountTable_UserTable]
GO
ALTER TABLE [dbo].[OrderDetailTable]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetailTable_BookTable] FOREIGN KEY([BookID])
REFERENCES [dbo].[BookTable] ([BookID])
GO
ALTER TABLE [dbo].[OrderDetailTable] CHECK CONSTRAINT [FK_OrderDetailTable_BookTable]
GO
ALTER TABLE [dbo].[OrderDetailTable]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetailTable_OrderTable] FOREIGN KEY([OrderID])
REFERENCES [dbo].[OrderTable] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetailTable] CHECK CONSTRAINT [FK_OrderDetailTable_OrderTable]
GO
ALTER TABLE [dbo].[OrderTable]  WITH CHECK ADD  CONSTRAINT [FK_OrderTable_UserTable] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[UserTable] ([UserID])
GO
ALTER TABLE [dbo].[OrderTable] CHECK CONSTRAINT [FK_OrderTable_UserTable]
GO
ALTER TABLE [dbo].[UserTable]  WITH CHECK ADD  CONSTRAINT [FK_UserTable_RoleTable] FOREIGN KEY([Role])
REFERENCES [dbo].[RoleTable] ([Role])
GO
ALTER TABLE [dbo].[UserTable] CHECK CONSTRAINT [FK_UserTable_RoleTable]
GO
USE [master]
GO
ALTER DATABASE [BookStore ] SET  READ_WRITE 
GO
