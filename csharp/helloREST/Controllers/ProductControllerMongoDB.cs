using helloREST.Models;
using System.Collections.Generic;  
using System.Threading.Tasks;  
using Microsoft.AspNetCore.Mvc;
using helloREST.Data;

namespace helloREST.Controllers
{
    [ApiController]
    [Route("api/v2/products")]
    public class ProductControllerMongoDB : Controller
    {
        private readonly IProductMongoRepo _repository;

        public ProductControllerMongoDB(IProductMongoRepo repository){
            _repository = repository;
        }

        [HttpGet]
        public async Task<ActionResult> Index(){
            IEnumerable<ProductMongoDB> products = await _repository.GetProducts();
            return Ok(products);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult> Details(string id){
            var productItem = await _repository.GetProduct(id);
            return Ok(productItem);
        }

        [HttpPost]
        public async Task<ActionResult> AddProduct(ProductMongoDB product){
            await _repository.Add(product);

            return Ok(product);
        }
        
        public async Task<ActionResult> UpdateProduct(ProductMongoDB product){
            await _repository.Update(product);

            return Ok(product);
        }

        [HttpPut("{id}")]
        public async Task<ActionResult> UpdateProductById(string id, ProductMongoDB product){
            await _repository.UpdateById(product, id);

            return Ok(product);
        }

        [HttpDelete("{id}")]
        public async Task DeleteProductById(string id){
             await _repository.Delete(id);
        }
    }
}