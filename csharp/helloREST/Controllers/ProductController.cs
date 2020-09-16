using System.Collections.Generic;
using helloREST.Data;
using helloREST.Models;
using Microsoft.AspNetCore.Mvc;

namespace helloREST.Controllers
{
    [ApiController]
    [Route("api/v1/products")]
    public class ProductController : ControllerBase
    {
        private readonly IProductRepo _repository;

        public ProductController(IProductRepo repository){
            _repository = repository;
        }
        //private readonly MockProductRepo _repository = new MockProductRepo();

        [HttpGet]
        public ActionResult <IEnumerable<Product>> GetAllComands(){
            var productItems = _repository.GetAppProducts();

            return Ok(productItems);    
        }

        [HttpGet("{id}")]
        public ActionResult <IEnumerable<Product>> GetCommandById(int id){
            var productItem = _repository.GetProductById(id);

            return Ok(productItem);     
        }
    }
}