package system.nsfw.user.service.impl;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import system.core.service.impl.BaseServiceImpl;
import system.nsfw.role.entity.Role;
import system.nsfw.user.dao.UserDao;
import system.nsfw.user.entity.User;
import system.nsfw.user.entity.UserRole;
import system.nsfw.user.entity.UserRoleId;
import system.nsfw.user.service.UserService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Administrator
 * @create 6/8
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }


    @Override
    public void importExcel(File headImgs, String headImgsFileName) {
        try {
            String regex = "^.+\\.(?i)(xls)$";
            //判断是否为.xls结尾
            boolean isExcel03 = headImgsFileName.matches(regex);
            FileInputStream inputStream = new FileInputStream(headImgs);
            Workbook workbook = isExcel03 ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = 2;
            if (sheet.getPhysicalNumberOfRows() > rows) {
                User user;
                for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                    user = new User();
                    Row row = sheet.getRow(i + 2);
                    Cell cell0 = row.getCell(0);
                    user.setName(cell0.getStringCellValue());
                    Cell cell1 = row.getCell(1);
                    user.setAccount(cell1.getStringCellValue());
                    Cell cell2 = row.getCell(2);
                    user.setDept(cell2.getStringCellValue());

                    Cell cell3 = row.getCell(3);
                    user.setGender("男".equals(cell3.getStringCellValue()));

                    Cell cell4 = row.getCell(4);
                    try {
                        user.setPhone(cell4.getStringCellValue());
                    } catch (Exception e) {
                        double value = cell4.getNumericCellValue();
                        String phone = BigDecimal.valueOf(value).toString();
                        user.setPhone(phone);
                    }

                    Cell cell5 = row.getCell(5);
                    user.setEmail(cell5.getStringCellValue());
                    Cell cell6 = row.getCell(6);
                    if (cell6.getDateCellValue() != null) {
                        user.setBirthday(cell6.getDateCellValue());
                    }
                    user.setPassword("123456");
                    user.setState(User.USER_STATE_VALID);
                    save(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportForExcle(List<User> userList, ServletOutputStream outputStream) {
        try {
            //创建excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建表头的样式
            HSSFCellStyle headStyle = setStyle(workbook, (short) 16);
            //创建标题的样式
            HSSFCellStyle totilStyle = setStyle(workbook, (short) 13);
            //合并的样式
            CellRangeAddress rangeAddress = new CellRangeAddress(0, 3, 0, 4);
            //创建工作表
            HSSFSheet sheet = workbook.createSheet();
            //设置默认的单元格宽度
            sheet.setDefaultColumnWidth(25);
            //添加合并样式
            sheet.addMergedRegion(rangeAddress);

            HSSFRow row = sheet.createRow(0);
            HSSFCell headCell = row.createCell(0);
            headCell.setCellValue("用户列表");
            headCell.setCellStyle(headStyle);

            HSSFRow row1 = sheet.createRow(4);
            String[] list = {"用户名", "账号", "所属部门", "性别", "电子邮箱"};
            for (int i = 0; i < list.length; i++) {
                HSSFCell cell = row1.createCell(i);
                cell.setCellStyle(totilStyle);
                cell.setCellValue(list[i]);
            }
            int i = 5;
            for (User user : userList) {
                HSSFRow row2 = sheet.createRow(i++);
                HSSFCell cell1 = row2.createCell(0);
                cell1.setCellValue(user.getName());
                HSSFCell cell2 = row2.createCell(1);
                cell2.setCellValue(user.getAccount());
                HSSFCell cell3 = row2.createCell(2);
                cell3.setCellValue(user.getDept());
                HSSFCell cell4 = row2.createCell(3);
                cell4.setCellValue(user.getGender() ? "男" : "女");
                HSSFCell cell5 = row2.createCell(4);
                cell5.setCellValue(user.getEmail());
            }
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean doVerify(String account, String id) {
        return userDao.doVerify(account, id);
    }

    @Override
    public void saveUserAndRole(User user, String... userRoleIds) {
        userDao.save(user);
        if (userRoleIds != null) {
            for (String id : userRoleIds) {
                userDao.saveUserRole(new UserRole(new UserRoleId(new Role(id), user.getId())));
            }
        }
    }

    @Override
    public void updateUserAndRole(User user, String... userRoleIds) {
        userDao.update(user);
        userDao.deleteUserRole(user.getId());
        if (userRoleIds != null) {
            for (String id : userRoleIds) {
                userDao.saveUserRole(new UserRole(new UserRoleId(new Role(id), user.getId())));
            }
        }

    }

    @Override
    public List<UserRole> getUserRoleListById(String id) {
        List<UserRole> list = userDao.getUserRoleListById(id);
        return list;
    }

    @Override
    public List<User> findUserByAccountAndPsd(User user) {
        return userDao.findUserByAccountAndPsd(user);

    }

    @Override
    public void deleteUserRole(String id) {
        userDao.deleteUserRole(id);
    }

    public HSSFCellStyle setStyle(HSSFWorkbook workbook, short fontSize) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontSize);
        font.setBold(true);
        style.setFont(font);
        return style;
    }

}