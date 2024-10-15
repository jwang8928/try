using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    public float Speed = 200;
    public float Spin = 20;
    private Rigidbody body;
    private Animator animator;
    public Transform sword;
    public float distance = 1F;

    // Start is called before the first frame update
    void Start()
    {
        body = GetComponent<Rigidbody>();
        animator = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetButtonDown("Fire1"))
        {
            animator.SetTrigger("Attacking");
            RaycastHit hit;
            if (Physics.Raycast(transform.position, transform.TransformDirection(Vector3.forward), out hit, 8f))
            {
                if (hit.transform.gameObject.CompareTag("Enemies"))
                {
                    SkeletonController enemy = hit.transform.gameObject.GetComponent<SkeletonController>();
                    enemy.GetHit();

                }
            }
        }
    }
    void FixedUpdate()
    {
        float direction = Input.GetAxis("Vertical") * Speed;
        float rotation = Input.GetAxis("Horizontal") * Spin;
        body.AddRelativeForce(new Vector3(0, 0, direction));
        animator.SetBool("Running", direction > 0);
        body.AddTorque(new Vector3(0, rotation, 0));
    }
}