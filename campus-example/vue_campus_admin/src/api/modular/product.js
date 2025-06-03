import request from '@/utils/request'

// 查询商品列表（分页）。只传递 name 作为模糊查询条件，后端接收到的 JSON 中不会包含 invalid 的 price 格式
export function listProduct(query) {
  // 只构造一个简单的过滤对象，例如 { name: 'xxx' }，不要传 price: '0,0' 这种非法字符串
  const data = {};
  if (query.name) {
    data.name = query.name;
  }
  // 如果后端以后支持 minPrice/maxPrice 则需要改对应接口。当前 Product 实体里只有 price 是 BigDecimal，后端 page 方法会直接用等值过滤
  return request({
    url: `/campus/product/list/${query.page}/${query.size}`,
    method: 'put',
    data: data
  });
}

// 获取商品详细
export function getProduct(productId) {
  return request({
    url: `/campus/product/${productId}`,
    method: 'get'
  });
}

// 新增商品
export function addProduct(data) {
  return request({
    url: `/campus/product/add`,
    method: 'post',
    data: data
  });
}

// 修改商品
export function updateProduct(data) {
  return request({
    url: `/campus/product/modify`,
    method: 'put',
    data: data
  });
}

// 删除商品，支持批量。ids 数组会 join(',') 构造为 /1,2,3
export function delProduct(ids) {
  return request({
    url: `/campus/product/${ids.join(',')}`,
    method: 'delete'
  });
}
