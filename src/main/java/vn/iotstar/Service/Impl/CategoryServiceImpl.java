package vn.iotstar.Service.Impl;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.iotstar.Entity.CategoryEntity;
import vn.iotstar.Repository.CategoryRepository;
import vn.iotstar.Service.ICategoryService;

//khai báo service
@Service
public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	CategoryRepository categoryRepository;

//source -> Generate Constructor using Field, xóa super()
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public <S extends CategoryEntity> S save(S entity) {
		// Kiểm tra nếu chưa có categoryId (thực thể mới)
		if (entity.getCategoryId() == null) {
			return categoryRepository.save(entity); // Lưu thực thể vào database
		} else {
			// Tìm kiếm thực thể theo ID
			Optional<CategoryEntity> opt = findById(entity.getCategoryId());
			if (opt.isPresent()) { // Kiểm tra nếu tồn tại
				// Kiểm tra nếu tên không được chỉ định (null hoặc rỗng)
				if (StringUtils.isEmpty(entity.getName())) {
					entity.setName(opt.get().getName()); // Giữ nguyên tên cũ
				} else {
					// Nếu có tên mới, sử dụng tên mới
					entity.setName(entity.getName());
				}
			}
			return categoryRepository.save(entity); // Lưu thực thể vào database
		}
	}

	@Override
	public List<CategoryEntity> findAll() {
		return categoryRepository.findAll(); // Lấy tất cả các thực thể từ database
	}

	@Override
	public Page<CategoryEntity> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable); // Lấy danh sách thực thể có phân trang
	}

	@Override
	public List<CategoryEntity> findAll(Sort sort) {
		return categoryRepository.findAll(sort); // Lấy danh sách thực thể có sắp xếp
	}

	@Override
	public List<CategoryEntity> findAllById(Iterable<Long> ids) {
		return categoryRepository.findAllById(ids); // Lấy danh sách thực thể theo các ID
	}

	@Override
	public Optional<CategoryEntity> findById(Long id) {
		return categoryRepository.findById(id); // Tìm thực thể theo ID
	}

	public <S extends CategoryEntity> Optional<S> findOne(Example<S> example) {
		return categoryRepository.findOne(example); // Tìm một thực thể phù hợp với ví dụ
	}

	@Override
	public long count() {
		return categoryRepository.count(); // Đếm tổng số thực thể
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id); // Xóa thực thể theo ID
	}

	@Override
	public void delete(CategoryEntity entity) {
		categoryRepository.delete(entity); // Xóa thực thể được truyền vào
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll(); // Xóa tất cả các thực thể
	}

	@Override
	public List<CategoryEntity> findByNameContaining(String name) {
		return categoryRepository.findByNameContaining(name); // Tìm các thực thể có tên chứa chuỗi "name"
	}

	@Override
	public Page<CategoryEntity> findByNameContaining(String name, Pageable pageable) {
		return categoryRepository.findByNameContaining(name, pageable); 
	}

	@Override
	public Optional<CategoryEntity> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}